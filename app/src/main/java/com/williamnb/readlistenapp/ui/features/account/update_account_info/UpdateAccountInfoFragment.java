package com.williamnb.readlistenapp.ui.features.account.update_account_info;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.williamnb.readlistenapp.R;
import com.williamnb.readlistenapp.base.BaseFragment;
import com.williamnb.readlistenapp.data.local.models.SettingItem;
import com.williamnb.readlistenapp.databinding.FragmentUpdateAccountInfoBinding;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Author: William Giang Nguyen | 15/04/2022
 * */
public class UpdateAccountInfoFragment extends BaseFragment<
        FragmentUpdateAccountInfoBinding,
        UpdateAccountInfoViewModel>
        implements UpdateAccountInfoAdapter.UpdateAccountInfoCallBack {

    private String encodeImage;
    private static final int RESULT_OK = -1;
    private final ActivityResultLauncher<Intent> pickImage = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        if (result.getData() != null) {
                            Uri imageUri = result.getData().getData();
                            try {
                                InputStream inputStream = requireContext().getContentResolver().openInputStream(imageUri);
                                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                                viewBinding.widgetUpdateDisplayInfo.imvAvatar.setImageBitmap(bitmap);
                                encodeImage = viewModel.encodeImage(bitmap);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });

    @Override
    public UpdateAccountInfoViewModel createViewModel() {
        return new ViewModelProvider(this).get(UpdateAccountInfoViewModel.class);
    }

    @Override
    public FragmentUpdateAccountInfoBinding createViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentUpdateAccountInfoBinding.inflate(inflater, container, false);
    }

    @Override
    public void initializeView() {
        hideBottomNavigationView(true);

        //bindView for display information
        viewBinding.widgetUpdateDisplayInfo.flUpdateDisplayInfo.setVisibility(View.VISIBLE);
        viewBinding.widgetUpdateDisplayInfo.cvUpdateDisplayInfo.setVisibility(View.VISIBLE);
        viewBinding.widgetUpdateDisplayInfo.expand.setImageResource(R.drawable.ic_expand_less);
        //bindView for avatar
        byte[] bytes = Base64.decode(viewModel.getAvatar(), Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        viewBinding.widgetUpdateDisplayInfo.imvAvatar.setImageBitmap(bitmap);
        //bindView for username
        viewBinding.widgetUpdateDisplayInfo.edtName.setText(viewModel.getUserName());
        viewBinding.widgetUpdateDisplayInfo.edtName.setEnabled(true);
        viewBinding.widgetUpdateDisplayInfo.edtName.setVisibility(View.VISIBLE);
        //bindView for email (email cannot change)
        viewBinding.widgetUpdateDisplayInfo.edtEmail.setText(viewModel.getEmail());
        viewBinding.widgetUpdateDisplayInfo.edtEmail.setEnabled(false);
        viewBinding.widgetUpdateDisplayInfo.edtEmail.setVisibility(View.VISIBLE);
        //-----------------------------------------------------------------------
        //bindView for change password
        viewBinding.widgetChangePassword.flChangePass.setVisibility(View.VISIBLE);
        viewBinding.widgetChangePassword.cvChangePass.setVisibility(View.GONE);
        viewBinding.widgetChangePassword.expand.setImageResource(R.drawable.ic_select_option);
        viewBinding.widgetChangePassword.edtOldPass.setVisibility(View.VISIBLE);
        viewBinding.widgetChangePassword.edtOldPass.setEnabled(true);
        viewBinding.widgetChangePassword.edtNewPass.setVisibility(View.VISIBLE);
        viewBinding.widgetChangePassword.edtNewPass.setEnabled(true);
        viewBinding.widgetChangePassword.edtConfirmPass.setVisibility(View.VISIBLE);
        viewBinding.widgetChangePassword.edtConfirmPass.setEnabled(true);

        //bindView for setting list
        viewBinding.flSetting.setVisibility(View.VISIBLE);
        viewBinding.flRcvSettings.setVisibility(View.GONE);
        viewBinding.expandSetting.setImageResource(R.drawable.ic_select_option);
    }

    @Override
    public void initializeComponent() {
        UpdateAccountInfoAdapter adapter = new UpdateAccountInfoAdapter(viewModel.getSettingItemList(), this);
        viewBinding.rcvSettings.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        viewBinding.rcvSettings.addItemDecoration(new DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL));
        viewBinding.rcvSettings.setAdapter(adapter);
    }

    @Override
    public void initializeEvents() {
        viewBinding.btnBack.setOnClickListener(v -> {
            Log.d(UpdateAccountInfoFragment.class.getName(), "debug: back clicked");
            findNavController().popBackStack();
        });

        viewBinding.save.setOnClickListener(v -> {
            Log.d(UpdateAccountInfoFragment.class.getName(), "debug: save clicked");
            //save data for part display info
            viewModel.putDisplayInformation(this.encodeImage,
                    viewBinding.widgetUpdateDisplayInfo.edtName.getText().toString().trim(),
                    viewBinding.widgetUpdateDisplayInfo.edtEmail.getText().toString().trim()
            );
            //save data for part change pass (if have)
            viewModel.putChangePassword();
            findNavController().popBackStack();
        });

        viewBinding.widgetUpdateDisplayInfo.imvAvatar.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            pickImage.launch(intent);
        });

        viewBinding.widgetUpdateDisplayInfo.flUpdateDisplayInfo.setOnClickListener(v -> {
            viewModel.setShowMoreDisplayInfo(!viewModel.isShowMoreDisplayInfo());
            if (viewModel.isShowMoreDisplayInfo()) {
                viewBinding.widgetUpdateDisplayInfo.cvUpdateDisplayInfo.setVisibility(View.VISIBLE);
                viewBinding.widgetUpdateDisplayInfo.expand.setImageResource(R.drawable.ic_expand_less);
            } else {
                viewBinding.widgetUpdateDisplayInfo.cvUpdateDisplayInfo.setVisibility(View.GONE);
                viewBinding.widgetUpdateDisplayInfo.expand.setImageResource(R.drawable.ic_select_option);
            }
        });

        viewBinding.widgetChangePassword.flChangePass.setOnClickListener(v -> {
            viewModel.setShowMoreChangePass(!viewModel.isShowMoreChangePass());
            if (viewModel.isShowMoreChangePass()) {
                viewBinding.widgetChangePassword.cvChangePass.setVisibility(View.VISIBLE);
                viewBinding.widgetChangePassword.expand.setImageResource(R.drawable.ic_expand_less);
            } else {
                viewBinding.widgetChangePassword.cvChangePass.setVisibility(View.GONE);
                viewBinding.widgetChangePassword.expand.setImageResource(R.drawable.ic_select_option);
            }
        });

        viewBinding.flSetting.setOnClickListener(v -> {
            viewModel.setShowMoreSettings(!viewModel.isShowMoreSettings());
            if (viewModel.isShowMoreSettings()) {
                viewBinding.flRcvSettings.setVisibility(View.VISIBLE);
                viewBinding.expandSetting.setImageResource(R.drawable.ic_expand_less);
            } else {
                viewBinding.flRcvSettings.setVisibility(View.GONE);
                viewBinding.expandSetting.setImageResource(R.drawable.ic_select_option);
            }
        });
    }

    @Override
    public void initializeData() {
    }

    @Override
    public void onClicked(@NonNull SettingItem item) {
        Toast.makeText(requireContext(), item.getText() + " " + item.isSwitch(), Toast.LENGTH_SHORT).show();
    }
}
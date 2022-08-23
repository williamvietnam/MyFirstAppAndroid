package com.williamnb.readlistenapp.ui.features.account;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.williamnb.readlistenapp.R;
import com.williamnb.readlistenapp.base.BaseFragment;
import com.williamnb.readlistenapp.databinding.FragmentAccountBinding;
import com.williamnb.readlistenapp.ui.features.login.SignInActivity;
import com.williamnb.readlistenapp.utilities.Constants;

public class AccountFragment extends BaseFragment<FragmentAccountBinding, AccountViewModel> implements AccountAdapter.AccountCallBack {

    private AccountAdapter adapter;

    @Override
    public FragmentAccountBinding createViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentAccountBinding.inflate(inflater, container, false);
    }

    @Override
    public AccountViewModel createViewModel() {
        return new ViewModelProvider(this).get(AccountViewModel.class);
    }

    @Override
    public void initializeView() {
        hideBottomNavigationView(false);

        //setup userInfo
        byte[] bytes = Base64.decode(viewModel.getAvatar(), Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        viewBinding.imvAvatar.setImageBitmap(bitmap);
        viewBinding.tvFullName.setText(viewModel.getFullName());
        viewBinding.tvEmail.setText(viewModel.getEmail());

        //setup recyclerview
        this.adapter = new AccountAdapter(viewModel.getItemList(), this);

        viewBinding.rcv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        viewBinding.rcv.addItemDecoration(new DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL));
        viewBinding.rcv.setAdapter(this.adapter);
    }

    @Override
    public void initializeComponent() {
        final Observer<Boolean> logoutObserver = new Observer<Boolean>() {
            @Override
            public void onChanged(@NonNull Boolean isLogout) {
                if (isLogout) {
                    Intent intent = new Intent(requireActivity(), SignInActivity.class);
                    startActivity(intent);
                    requireActivity().finish();
                }
            }
        };
        viewModel.getLogoutLiveData().observe(this, logoutObserver);
    }

    @Override
    public void initializeEvents() {
        viewBinding.imvAvatar.setOnClickListener(v -> new AlertDialog.Builder(requireContext())
                .setTitle("Cập nhật thông tin")
                .setMessage("Bạn có muốn cập nhật lại ảnh đại diện không?")
                .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        findNavController().navigate(R.id.actionAccountToUpdateAccountInfo);
                        Log.d(AccountFragment.class.getName(), "debug: avatar edit clicked");
                    }
                })
                .setNegativeButton("Huỷ", null)
                .setIcon(R.drawable.ic_edit)
                .setCancelable(true)
                .show());

        viewBinding.tvFullName.setOnClickListener(v -> new AlertDialog.Builder(requireContext())
                .setTitle("Cập nhật thông tin")
                .setMessage("Bạn có muốn cập nhật lại tên cá nhân không?")
                .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        findNavController().navigate(R.id.actionAccountToUpdateAccountInfo);
                        Log.d(AccountFragment.class.getName(), "debug: full name edit clicked");
                    }
                })
                .setNegativeButton("Huỷ", null)
                .setIcon(R.drawable.ic_edit)
                .setCancelable(true)
                .show());
    }

    @Override
    public void initializeData() {
    }

    @Override
    public void onClicked(@NonNull String itemId) {
        switch (itemId) {
            case Constants.ACCOUNT_ITEM_PRIVACY:
                findNavController().navigate(R.id.actionAccountToUpdateAccountInfo);
                break;
            case Constants.ACCOUNT_BACKUP_RESTORE:
                Toast.makeText(getContext(), "Đang phát triển: " + Constants.ACCOUNT_BACKUP_RESTORE, Toast.LENGTH_SHORT).show();
                break;
            case Constants.ACCOUNT_ITEM_NOTIFICATION:
                Toast.makeText(getContext(), "Đang phát triển: " + Constants.ACCOUNT_ITEM_NOTIFICATION, Toast.LENGTH_SHORT).show();
                break;
            case Constants.ACCOUNT_ITEM_LANGUAGE:
                Toast.makeText(getContext(), "Đang phát triển: " + Constants.ACCOUNT_ITEM_LANGUAGE, Toast.LENGTH_SHORT).show();
                break;
            case Constants.ACCOUNT_ITEM_ABOUT_US:
                findNavController().navigate(R.id.actionAccountToAboutUs);
                break;
            case Constants.ACCOUNT_ITEM_LOG_OUT:
                new AlertDialog.Builder(requireContext())
                        .setTitle("Đăng xuất")
                        .setMessage("Bạn có chắc chắn muốn đăng xuất không?")
                        .setIcon(R.drawable.ic_logout)
                        .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                viewModel.logout();
                            }
                        })
                        .setNegativeButton("Hủy", null)
                        .setCancelable(true)
                        .show();
                break;
        }
    }
}
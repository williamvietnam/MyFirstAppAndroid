package com.williamnb.readlistenapp.ui.custom.toolbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

import com.squareup.picasso.Picasso;
import com.williamnb.readlistenapp.R;
import com.williamnb.readlistenapp.databinding.ToolbarCustomViewBinding;

public class ToolbarCustom extends FrameLayout {

    private ToolbarCustomViewBinding binding;
    private ToolbarLeftCallBack leftCallBack;
    private ToolbarRightCallBack rightCallBack;

    public void setToolbarLeftCallBack(ToolbarLeftCallBack leftCallBack) {
        this.leftCallBack = leftCallBack;
    }

    public void setToolbarRightCallBack(ToolbarRightCallBack rightCallBack) {
        this.rightCallBack = rightCallBack;
    }

    public ToolbarCustom(@NonNull Context context) {
        super(context);
        initialize(context, null);
    }

    public ToolbarCustom(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize(context, attrs);
    }

    public ToolbarCustom(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context, attrs);
    }

    private void initialize(Context context, AttributeSet attrs) {
        LayoutInflater inflater = LayoutInflater.from(context);
        binding = ToolbarCustomViewBinding.inflate(inflater, this, true);

        binding.leftImageView.setOnClickListener(v -> {
            if (leftCallBack != null) {
                leftCallBack.onLeftClicked();
            } else {
                Log.d(ToolbarCustom.class.getSimpleName(), "leftCallBack not initialization");
            }
        });

        binding.rightImageView.setOnClickListener(v -> {
            if (rightCallBack != null) {
                rightCallBack.onRightClicked();
            } else {
                Log.d(ToolbarCustom.class.getSimpleName(), "rightCallBack not initialization");
            }
        });

        if (attrs != null) {
            final TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ToolbarCustom, 0, 0);

            Drawable leftIcon = typedArray.getDrawable(R.styleable.ToolbarCustom_leftIcon);
            if (leftIcon != null) {
                binding.leftImageView.setVisibility(VISIBLE);
                binding.leftImageView.setImageDrawable(leftIcon);
            } else {
                binding.leftImageView.setVisibility(GONE);
            }

            String toolbarName = typedArray.getString(R.styleable.ToolbarCustom_toolbarName);
            if (toolbarName != null) {
                binding.toolbarName.setVisibility(VISIBLE);
                binding.toolbarName.setText(toolbarName);
            } else {
                binding.toolbarName.setVisibility(GONE);
            }

            Drawable rightIcon = typedArray.getDrawable(R.styleable.ToolbarCustom_rightIcon);
            if (rightIcon != null) {
                binding.rightImageView.setVisibility(VISIBLE);
                binding.rightImageView.setImageDrawable(rightIcon);
            } else {
                binding.rightImageView.setVisibility(GONE);
            }
        }
    }

    /**
     * load icon from internet
     */
    @Deprecated
    public void setLeftIconFromInternet(String url) {
        Picasso.get()
                .load(url)
                .placeholder(R.drawable.ic_back)
                .error(R.drawable.ic_back)
                .into(binding.leftImageView);
    }

    /**
     * load icon from internet
     */
    @Deprecated
    public void setRightIconFromInternet(String url) {
        Picasso.get()
                .load(url)
                .placeholder(R.drawable.ic_search)
                .error(R.drawable.ic_search)
                .into(binding.rightImageView);
    }

    public void setLeftIconResource(@DrawableRes int leftIconResource) {
        binding.leftImageView.setImageResource(leftIconResource);
    }

    public void setRightIconResource(@DrawableRes int rightIconResource) {
        binding.rightImageView.setImageResource(rightIconResource);
    }

    public void setToolbarNameResource(@StringRes int textResource) {
        binding.toolbarName.setText(textResource);
    }

    public void setToolbarNameResource(String text) {
        binding.toolbarName.setText(text);
    }

    public interface ToolbarLeftCallBack {
        void onLeftClicked();
    }

    public interface ToolbarRightCallBack {
        void onRightClicked();
    }
}
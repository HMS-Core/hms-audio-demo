package com.huawei.hms.audiokitdemo.equalizer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.huawei.hms.audiokit.player.soundeffect.callback.GetSoundEffectListCallback;
import com.huawei.hms.audiokit.player.soundeffect.callback.GetSoundEffectListListener;
import com.huawei.hms.audiokit.soundeffect.bean.HwAudioEffectItem;
import com.huawei.hms.audiokitdemo.R;
import com.huawei.hms.audiokitdemo.constant.SoundEffectConstant;
import com.huawei.hms.audiokitdemo.helper.SoundEffectHelper;
import com.huawei.hms.audiokitdemo.utils.ResUtils;
import com.huawei.hms.audiokitdemo.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * [自定义编辑音效dialog,可重命名当前自定义音效]<BR>
 *
 * @author dWX814841
 * @version [V12.11.9.x, 2020/3/16]
 * @since 12.11.9.x
 */
public class SoundRenameDialog extends DialogFragment {
    private static final String TAG = "SoundRenameDialog";

    private ButtonClickListener mListener;

    public static SoundRenameDialog newInstance() {
        return new SoundRenameDialog();
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.sound_dialog_rename_content_layout, null);
        final EditText renameSoundEffect = ViewUtils.findViewById(view, R.id.rename_sound_effect);
        builder.setView(view);
        builder.setTitle(ResUtils.getString(R.string.rename_sound_effect));
        builder.setPositiveButton(ResUtils.getString(R.string.save), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String name = renameSoundEffect.getText().toString();
                if (mListener != null) {
                    mListener.onPositiveButton(name);
                }
            }
        });
        SoundEffectHelper.getInstance().getOrdinarySoundEffect(new GetSoundEffectListCallback(new GetSoundEffectListListener() {
            @Override
            public void onSuccess(List<HwAudioEffectItem> list) {
                if (list == null) {
                    return;
                }
                if (renameSoundEffect != null) {
                    renameSoundEffect.setText("我的音效" + getCustomSoundSize(list));
                }
            }

            @Override
            public void onError(int i, String s) {
                Log.i(TAG, "onError " + s);
            }
        }));
        builder.setNegativeButton(ResUtils.getString(R.string.cancle), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (mListener != null) {
                    mListener.onNegativeButton();
                }
            }
        });
        return builder.create();

    }

    private String getCustomSoundSize(List<HwAudioEffectItem> list) {
        if (list == null) {
            return "";
        }
        int count = 0;
        for (HwAudioEffectItem audioEffectItem : list) {
            if (audioEffectItem == null) {
                continue;
            }
            if (audioEffectItem.getType() == SoundEffectConstant.ORDINARY_SOUNDEFFECT_CUSTOM_TYPE) {
                count++;
            }
        }
        return count == 0 ? "" : String.valueOf(count);
    }

    public void setListener(ButtonClickListener listener) {
        mListener = listener;
    }

    public interface ButtonClickListener {
        void onPositiveButton(String name);
        void onNegativeButton();
    }
}
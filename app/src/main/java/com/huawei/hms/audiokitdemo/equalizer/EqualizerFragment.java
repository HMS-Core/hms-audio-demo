
package com.huawei.hms.audiokitdemo.equalizer;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.huawei.hms.audiokit.player.soundeffect.callback.GetSoundEffectListCallback;
import com.huawei.hms.audiokit.player.soundeffect.callback.GetSoundEffectListListener;
import com.huawei.hms.audiokit.player.soundeffect.callback.SetSoundEffectCallback;
import com.huawei.hms.audiokit.player.soundeffect.callback.SetSoundEffectListener;
import com.huawei.hms.audiokit.soundeffect.bean.HwAudioEffectItem;
import com.huawei.hms.audiokitdemo.R;
import com.huawei.hms.audiokitdemo.constant.SoundEffectConstant;
import com.huawei.hms.audiokitdemo.helper.SoundEffectHelper;
import com.huawei.hms.audiokitdemo.ui.effect.BaseUIFragment;
import com.huawei.hms.audiokitdemo.ui.effect.SoundEffectOrdinaryFragment;
import com.huawei.hms.audiokitdemo.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * [均衡器主界面Fragment]<BR>
 *
 * @author dWX814841
 * @version [V12.11.9.1, 2020/3/3]
 * @since 12.11.9.x
 */
public class EqualizerFragment extends BaseUIFragment implements SoundRenameDialog.ButtonClickListener {

    private static final String TAG = "EqualizerFragment";

    private HwAudioEffectItem mHwAudioEffectItem;

    private int MIN_MARK = -10;
    private int MIN_CHNNEL_MARK = -5;
    private int MAX_MARK = 10;
    private int MAX_CHNNE_MARK = 5;

    public static EqualizerFragment newInstance() {
        return new EqualizerFragment();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected int getContentViewLayout() {
        return R.layout.fragment_equalizer;
    }

    @Override
    protected void initViews(View view) {
        Log.i(TAG, "initViews");
        EditText eq31 = ViewUtils.findViewById(view, R.id.EQ_31);
        EditText eq62 = ViewUtils.findViewById(view, R.id.EQ_62);
        EditText eq125 = ViewUtils.findViewById(view, R.id.EQ_125);
        EditText eq250 = ViewUtils.findViewById(view, R.id.EQ_250);
        EditText eq500 = ViewUtils.findViewById(view, R.id.EQ_500);
        EditText eq1k = ViewUtils.findViewById(view, R.id.EQ_1K);
        EditText eq2K = ViewUtils.findViewById(view, R.id.EQ_2K);
        EditText eq4K = ViewUtils.findViewById(view, R.id.EQ_4K);
        EditText eq8K = ViewUtils.findViewById(view, R.id.EQ_8K);
        EditText eq16K = ViewUtils.findViewById(view, R.id.EQ_16K);
        EditText ultraLowStress = ViewUtils.findViewById(view, R.id.ultra_low_stress);
        EditText encirclingSound = ViewUtils.findViewById(view, R.id.encircling_sound);
        EditText channelBalance = ViewUtils.findViewById(view, R.id.channel_balance);
        Button save = ViewUtils.findViewById(view, R.id.save);
        mHwAudioEffectItem = SoundEffectHelper.getInstance().createEffect("EqualizerFragment");
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundRenameDialog soundRenameDialog = SoundRenameDialog.newInstance();
                soundRenameDialog.setListener(EqualizerFragment.this);
                FragmentManager fragmentManager = getFragmentManager();
                if (fragmentManager != null) {
                    soundRenameDialog.show(fragmentManager, "EqualizerFragment");
                }
            }
        });
        addEditTextListener(eq31);
        addEditTextListener(eq62);
        addEditTextListener(eq125);
        addEditTextListener(eq250);
        addEditTextListener(eq1k);
        addEditTextListener(eq500);
        addEditTextListener(eq4K);
        addEditTextListener(eq8K);
        addEditTextListener(eq16K);
        addEditTextListener(eq2K);
        addEditTextListener(ultraLowStress);
        addEditTextListener(encirclingSound);
        addEditTextListener(channelBalance);
    }

    private void addEditTextListener(final EditText editText) {
        if (editText == null) {
            Log.i(TAG, "addEditTextListener is null");
            return;
        }
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (start >= 0) {//从一输入就开始判断，
                    int number = 0;
                    int num = parseInt(s.toString());
                    boolean isChannel = true;
                    if (editText.getId() == R.id.EQ_31) {
                        number = getEqNumber(s.toString());
                        isChannel = false;
                    } else if (editText.getId() == R.id.EQ_62) {
                        isChannel = false;
                        number = getEqNumber(s.toString());
                    } else if (editText.getId() == R.id.EQ_125) {
                        isChannel = false;
                        number = getEqNumber(s.toString());
                    } else if (editText.getId() == R.id.EQ_250) {
                        isChannel = false;
                        number = getEqNumber(s.toString());
                    } else if (editText.getId() == R.id.EQ_500) {
                        isChannel = false;
                        number = getEqNumber(s.toString());
                    } else if (editText.getId() == R.id.EQ_1K) {
                        isChannel = false;
                        number = getEqNumber(s.toString());
                    } else if (editText.getId() == R.id.EQ_2K) {
                        isChannel = false;
                        number = getEqNumber(s.toString());
                    } else if (editText.getId() == R.id.EQ_4K) {
                        isChannel = false;
                        number = getEqNumber(s.toString());
                    } else if (editText.getId() == R.id.EQ_8K) {
                        isChannel = false;
                        number = getEqNumber(s.toString());
                    } else if (editText.getId() == R.id.EQ_16K) {
                        isChannel = false;
                        number = getEqNumber(s.toString());
                    } else if (editText.getId() == R.id.ultra_low_stress) {
                        number = getChannelNumber(s.toString());
                    } else if (editText.getId() == R.id.encircling_sound) {
                        number = getChannelNumber(s.toString());
                    } else if (editText.getId() == R.id.channel_balance) {
                        number = getChannelNumber(s.toString());
                    }
                    if (isChannel) {
                        if (num >= MIN_CHNNEL_MARK && num <= MAX_CHNNE_MARK) {
                            return;
                        }
                    } else {
                        if (num >= MIN_MARK && num <= MAX_MARK) {
                            return;
                        }
                    }
                    editText.setText(String.valueOf(number));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mHwAudioEffectItem == null) {
                    Log.i(TAG, "afterTextChanged mHwAudioEffectItem is null");
                    return;
                }
                if (s == null) {
                    Log.i(TAG, "input is empty");
                    return;
                }
                Log.i(TAG, "afterTextChanged   " + s.toString());
                String text = s.toString();
                if (editText.getId() == R.id.EQ_31) {
                    mHwAudioEffectItem.setEq31(getEqNumber(text));
                } else if (editText.getId() == R.id.EQ_62) {
                    mHwAudioEffectItem.setEq62(getEqNumber(text));
                } else if (editText.getId() == R.id.EQ_125) {
                    mHwAudioEffectItem.setEq125(getEqNumber(text));
                } else if (editText.getId() == R.id.EQ_250) {
                    mHwAudioEffectItem.setEq250(getEqNumber(text));
                } else if (editText.getId() == R.id.EQ_500) {
                    mHwAudioEffectItem.setEq500(getEqNumber(text));
                } else if (editText.getId() == R.id.EQ_1K) {
                    mHwAudioEffectItem.setEq1k(getEqNumber(text));
                } else if (editText.getId() == R.id.EQ_2K) {
                    mHwAudioEffectItem.setEq2k(getEqNumber(text));
                } else if (editText.getId() == R.id.EQ_4K) {
                    mHwAudioEffectItem.setEq4k(getEqNumber(text));
                } else if (editText.getId() == R.id.EQ_8K) {
                    mHwAudioEffectItem.setEq8k(getEqNumber(text));
                } else if (editText.getId() == R.id.EQ_16K) {
                    mHwAudioEffectItem.setEq16k(getEqNumber(text));
                } else if (editText.getId() == R.id.ultra_low_stress) {
                    mHwAudioEffectItem.setEqUltraLowStress(getChannelNumber(text));
                } else if (editText.getId() == R.id.encircling_sound) {
                    mHwAudioEffectItem.setEqSuperEnvironmentalSoundEffect(getChannelNumber(text));
                } else if (editText.getId() == R.id.channel_balance) {
                    mHwAudioEffectItem.setEqSuperChannelBalance(getChannelNumber(text));
                }
            }
        });
    }

    private int getEqNumber(String number) {
        int eqNumber = parseInt(number);
        return eqNumber >= 0 ? Math.min(eqNumber, 10) : Math.max(eqNumber, -10);
    }

    private int getChannelNumber(String number) {
        int eqNumber = parseInt(number);
        return eqNumber >= 0 ? Math.min(eqNumber, 5) : Math.max(eqNumber, -5);
    }

    private int parseInt(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            Log.i(TAG, "error NumberFormatException");
        }
        return 0;
    }

    @Override
    public void onPositiveButton(String name) {
        HwAudioEffectItem equalizerFragment = SoundEffectHelper.getInstance().createEffect("EqualizerFragment");
        // 去生效自定义音效
        if (equalizerFragment != null && mHwAudioEffectItem != null) {
            equalizerFragment.setName(name);
            equalizerFragment.setType(SoundEffectConstant.ORDINARY_SOUNDEFFECT_CUSTOM_TYPE);
            equalizerFragment.setEq31(mHwAudioEffectItem.getEq31());
            equalizerFragment.setEq62(mHwAudioEffectItem.getEq62());
            equalizerFragment.setEq125(mHwAudioEffectItem.getEq125());
            equalizerFragment.setEq250(mHwAudioEffectItem.getEq250());
            equalizerFragment.setEq500(mHwAudioEffectItem.getEq500());
            equalizerFragment.setEq1k(mHwAudioEffectItem.getEq1k());
            equalizerFragment.setEq2k(mHwAudioEffectItem.getEq2k());
            equalizerFragment.setEq4k(mHwAudioEffectItem.getEq4k());
            equalizerFragment.setEq8k(mHwAudioEffectItem.getEq8k());
            equalizerFragment.setEq16k(mHwAudioEffectItem.getEq16k());
            equalizerFragment.setEqUltraLowStress(mHwAudioEffectItem.getEqUltraLowStress());
            equalizerFragment.setEqSuperEnvironmentalSoundEffect(mHwAudioEffectItem.getEqSuperEnvironmentalSoundEffect());
            equalizerFragment.setEqSuperChannelBalance(mHwAudioEffectItem.getEqSuperChannelBalance());

        }
        if (!SoundEffectHelper.getInstance().isEffectOn()) {
            SoundEffectHelper.getInstance().setEffectSwitch(true);
        }
        SoundEffectHelper.getInstance().setEffect(equalizerFragment, new SetSoundEffectCallback(new EqSetSoundEffectListener()));
        Log.i(TAG, "onPositiveButton to save Hw Audio Effect");
        SoundEffectHelper.getInstance().insertEffect(equalizerFragment);
    }

    @Override
    public void onNegativeButton() {

    }

    static class EqSetSoundEffectListener implements SetSoundEffectListener {
        @Override
        public void onSuccess(int i) {
            Log.i(TAG, "onSuccess---->" + i);
        }

        @Override
        public void onError(int i, String s) {
            Log.i(TAG, "onError" + i);
        }
    }
}

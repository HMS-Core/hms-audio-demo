package com.huawei.hms.audiokitdemo.helper;

import android.os.RemoteException;
import android.util.Log;

import com.huawei.hms.audiokit.player.manager.HwAudioEffectManager;
import com.huawei.hms.audiokit.player.soundeffect.callback.GetSoundEffectCallback;
import com.huawei.hms.audiokit.player.soundeffect.callback.GetSoundEffectListCallback;
import com.huawei.hms.audiokit.player.soundeffect.callback.SetSoundEffectCallback;
import com.huawei.hms.audiokit.soundeffect.bean.HwAudioEffectItem;
import com.huawei.hms.audiokitdemo.sdk.PlayHelper;


public class SoundEffectHelper {
    private static final String TAG = "SoundEffectHelper";

    private static class SoundEffectFactory {
        public static final SoundEffectHelper INSTANCE = new SoundEffectHelper();
    }

    public static SoundEffectHelper getInstance() {
        return SoundEffectFactory.INSTANCE;
    }

    /**
     * getEffect by resId
     *
     * @param resId    the res id
     * @param callback callback
     */
    public void getEffect(String resId, GetSoundEffectCallback callback) {
        HwAudioEffectManager manager = getHwAudioEffectManager();
        if (manager == null)
            return;
        manager.getEffect(resId, callback);
    }

    /**
     * set switch
     *
     * @param effectSwitch the effect switch
     */
    public void setEffectSwitch(boolean effectSwitch) {
        HwAudioEffectManager manager = getHwAudioEffectManager();
        if (manager == null)
            return;
        manager.setEffectSwitch(effectSwitch);
    }

    /**
     * create use
     *
     * @param effectName the effect name
     * @return the sound effect info interface
     */
    public HwAudioEffectItem createEffect(String effectName) {
        HwAudioEffectManager manager = getHwAudioEffectManager();
        if (manager == null)
            return null;
        return manager.createEffect(effectName);
    }

    /**
     * set use
     *
     * @param soundEffectInfoInterface the sound effect info interface
     * @param callback                 the callback
     */
    public void setEffect(HwAudioEffectItem soundEffectInfoInterface, SetSoundEffectCallback callback) {
        HwAudioEffectManager manager = getHwAudioEffectManager();
        if (manager == null)
            return;
        manager.setEffect(soundEffectInfoInterface, callback);
    }

    /**
     * setSoundOrdinaryLightOn
     * @param isUse true open
     */
    public void setSoundOrdinaryLightOn(boolean isUse) {
        HwAudioEffectManager manager = getHwAudioEffectManager();
        if (manager == null)
            return;
        manager.setSoundOrdinaryLightOn(isUse);
    }

    /**
     * setSoundChosenLightOn
     * @param isUse true open
     */
    public void setSoundChosenLightOn(boolean isUse) {
        HwAudioEffectManager manager = getHwAudioEffectManager();
        if (manager == null)
            return;
        manager.setSoundChosenLightOn(isUse);
    }

    /**
     * getSoundChosenLightOn
     * @return true open
     */
    public boolean getSoundChosenLightOn() {
        HwAudioEffectManager manager = getHwAudioEffectManager();
        if (manager == null)
            return false;
        return manager.getSoundChosenLightOn();
    }

    /**
     * getSoundOrdinaryLightOn
     * @return true open
     */
    public boolean getSoundOrdinaryLightOn() {
        HwAudioEffectManager manager = getHwAudioEffectManager();
        if (manager == null)
            return false;
        return manager.getSoundOrdinaryLightOn();
    }

    /**
     * isEffectOn
     *
     * @return true open
     */
    public boolean isEffectOn() {
        HwAudioEffectManager manager = getHwAudioEffectManager();
        if (manager == null)
            return false;
        return manager.isEffectOn();
    }


    /**
     * getNowUsingSoundEffect
     *
     * @return the now using sound effect
     */
    public HwAudioEffectItem getNowUsingSoundEffect() {
        HwAudioEffectManager manager = getHwAudioEffectManager();
        if (manager == null)
            return null;
        return manager.getNowUsingSoundEffect();
    }

    /**
     * getChosenSoundEffect
     *
     * @param callback the callback
     */
    public void getChosenSoundEffect(GetSoundEffectListCallback callback) {
        HwAudioEffectManager manager = getHwAudioEffectManager();
        if (manager == null)
            return ;
        manager.getChosenSoundEffect(callback);
    }

    /**
     * getOrdinarySoundEffect
     *
     * @param callback the callback
     */
    public void getOrdinarySoundEffect(GetSoundEffectListCallback callback) {
        HwAudioEffectManager manager = getHwAudioEffectManager();
        if (manager == null)
            return ;
        manager.getOrdinarySoundEffect(callback);
    }

    /**
     * insert or replace sound effect
     *
     * @param hwAudioEffectItem the sound effect info interface
     * @return the int
     */
    public int insertEffect(HwAudioEffectItem hwAudioEffectItem) {
        Log.i(TAG, "insertEffect");
        HwAudioEffectManager manager = getHwAudioEffectManager();
        if (manager == null)
            return -1;
        return manager.insertEffect(hwAudioEffectItem);
    }

    private HwAudioEffectManager getHwAudioEffectManager() {
        HwAudioEffectManager manager = PlayHelper.getInstance().getEffectManager();
        if (manager == null) {
            Log.i(TAG, "manager == null");
            return null;
        }
        return manager;
    }
}

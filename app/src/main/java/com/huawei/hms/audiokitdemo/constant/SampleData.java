/*
 * Copyright 2020. Huawei Technologies Co., Ltd. All rights reserved.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.huawei.hms.audiokitdemo.constant;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import com.huawei.hms.api.bean.HwAudioPlayItem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * SampleData
 *
 * @since 2020-06-01
 */
public class SampleData {

    private static final String TAG = "SampleData";

    /**
     * get online PlayList
     *
     * @return play list
     */
    public List<HwAudioPlayItem> getOnlinePlaylist() {
        List<HwAudioPlayItem> playItemList = new ArrayList<>();
        HwAudioPlayItem audioPlayItem4 = new HwAudioPlayItem();
        audioPlayItem4.setAudioId("1003");
        audioPlayItem4.setSinger("李昊瀚");
        audioPlayItem4.setOnlinePath("http://mpge.5nd.com/2007/2007new/s/11670/7.mp3");
        audioPlayItem4.setOnline(1);
        audioPlayItem4.setAudioTitle("如果爱能早些说出来");
        playItemList.add(audioPlayItem4);

        HwAudioPlayItem audioPlayItem5 = new HwAudioPlayItem();
        audioPlayItem5.setAudioId("1004");
        audioPlayItem5.setSinger("沙宝亮");
        audioPlayItem5.setOnlinePath("http://mpge.5nd.com/2010/2010b/2013-9-22/60951/8.mp3");
        audioPlayItem5.setOnline(1);
        audioPlayItem5.setAudioTitle("爱的省略号");
        playItemList.add(audioPlayItem5);

        HwAudioPlayItem audioPlayItem6 = new HwAudioPlayItem();
        audioPlayItem6.setAudioId("1005");
        audioPlayItem6.setSinger("徐誉滕");
        audioPlayItem6.setOnlinePath("http://mpge.5nd.com/2015/2015-3-9/66167/2.mp3");
        audioPlayItem6.setOnline(1);
        audioPlayItem6.setAudioTitle("偶尔回忆");
        playItemList.add(audioPlayItem6);

        return playItemList;
    }

    /**
     * get Local PlayList
     *
     * @return play list
     */
    public List<HwAudioPlayItem> getLocalPlayList(Context context) {
        List<HwAudioPlayItem> playItemList = new ArrayList<>();
        Cursor cursor = null;
        try {
            ContentResolver contentResolver = context.getContentResolver();
            if (contentResolver == null) {
                return playItemList;
            }
            cursor = contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                    null,
                    null,
                    null,
                    MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
            HwAudioPlayItem songItem;
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
                    if (new File(path).exists()) {
                        songItem = new HwAudioPlayItem();
                        songItem.setAudioTitle(
                                cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)));
                        songItem
                                .setAudioId(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)) + "");
                        songItem.setFilePath(path);
                        songItem
                                .setSinger(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)));
                        playItemList.add(songItem);
                    }
                }
            }
            songItem = new HwAudioPlayItem();
            songItem.setAudioTitle("chengshilvren");
            songItem.setAudioId("chengshilvren".hashCode() + "");
            songItem.setFilePath("hms_res://chengshilvren");
            songItem.setSinger("Taoge");
            playItemList.add(songItem);

            songItem = new HwAudioPlayItem();
            songItem.setAudioTitle("dayu");
            songItem.setAudioId("dayu".hashCode() + "");
            songItem.setFilePath("hms_assets://dayu.mp3");
            songItem.setSinger("Taoge");
            playItemList.add(songItem);
        } catch (Exception e) {
            Log.e(TAG, TAG, e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        Log.i(TAG, "getLocalPlayList: " + playItemList.size());
        return playItemList;
    }
}
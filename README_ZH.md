# 华为音频服务示例代码

[English](README.md) | 中文

## 目录

 * [简介](#简介)
 * [开发准备](#开发准备)
 * [环境要求](#环境要求)
 * [运行结果](#运行结果)
 * [授权许可](#授权许可)

## 简介
本示例代码封装华为音频服务（HUAWEI Audio Kit）安卓接口，提供多个样例项目供您参考。其中包含以下类：

**HwAudioPlayerManager**: 音频播放管理类（包括播放、暂停、上一首、下一首、停止播放、拖拽进度条）。

**HwAudioPlayItem**: 音频数据类。音频数据主要包括专辑名、艺术家名以及音频是否在线等。

**HwAudioQueueManager**: 音频列表管理类。比如从某个播放列表中删除特定音频。
	
## 开发准备

1. 检查Android Studio开发环境。在Android Studio中打开示例项目，其中含有 "build.gradle" 文件。

2. 在[华为开发者联盟](https://developer.huawei.com/consumer/en/?ha_source=hms1)上注册开发者账号。

3. 在AppGallery Connect上创建一个应用并配置应用信息。具体请参阅
具体请参阅[配置AppGallery Connect](https://developer.huawei.com/consumer/en/doc/development/Media-Guides/introduction-0000001050749665?ha_source=hms1)

4. 构建此Demo前，请将其导入Android Studio（3.0及以上版本）。

5. 在安卓设备或模拟机上运行测试应用。
	
## 环境要求
Android SDK：21及以上版本
JDK：1.7及以上版本

## 运行结果
<img src="audio.gif" width=30% > 

## 技术支持
如果您对HMS Core还处于评估阶段，可在[Reddit社区](https://www.reddit.com/r/HuaweiDevelopers/)获取关于HMS Core的最新讯息，并与其他开发者交流见解。

如果您对使用HMS示例代码有疑问，请尝试：
- 开发过程遇到问题上[Stack Overflow](https://stackoverflow.com/questions/tagged/huawei-mobile-services)，在`huawei-mobile-services`标签下提问，有华为研发专家在线一对一解决您的问题。
- 到[华为开发者论坛](https://developer.huawei.com/consumer/cn/forum/blockdisplay?fid=18) HMS Core板块与其他开发者进行交流。

如果您在尝试示例代码中遇到问题，请向仓库提交[issue](https://github.com/HMS-Core/hms-audio-demo/issues)，也欢迎您提交[Pull Request](https://github.com/HMS-Core/hms-audio-demo/pulls)。

##  授权许可
华为音频服务示例代码经过[Apache License, version 2.0](http://www.apache.org/licenses/LICENSE-2.0)授权许可。

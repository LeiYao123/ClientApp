#!/bin/bash

# 进入项目目录（可根据实际情况修改）
PROJECT_PATH=$(pwd)

#安卓包product文件夹路径
BUILD_PATH=${PROJECT_PATH}/apk/
# 打包APK的最初地址
RELEASE_PATH=${PROJECT_PATH}/app/build/outputs/apk/release/

# 定义模板和生成路径
TEMPLATE_FILE="template/env.tpl"
OUTPUT_FILE="app/src/main/java/com/quick/app/config/env.kt"
CREATED_AT=$(date "+%Y-%m-%d %H:%M:%S")

#VERSION_CODE=$(./gradlew properties | grep "versionCode:" | awk '{print $2}')
#VERSION_NAME=$(./gradlew properties | grep "versionName:" | awk '{print $2}')
#
#echo "VERSION_CODE: $VERSION_CODE"
#echo "VERSION_NAME: $VERSION_NAME"

VERSION_CODE=$(grep 'versionCode' app/build.gradle.kts | sed -E 's/.*versionCode = ([0-9]+).*/\1/')
VERSION_NAME=$(grep 'versionName' app/build.gradle.kts | sed -E 's/.*versionName = "(.*)".*/\1/')

echo "VERSION_CODE: $VERSION_CODE"
echo "VERSION_NAME: $VERSION_NAME"

# #如果有product/apk文件夹则删除，然后再创建一个空文件夹
if [ -d "${BUILD_PATH}" ]; then
  rm -rf "${BUILD_PATH}"
fi
# #创建apk目录
mkdir -p "${BUILD_PATH}"

#====================================================================
## 🔹 构建 Production 版本
BUILD_ENV="PRODUCTION"

# 替换占位符并生成代码文件
sed -e "s/{{BUILD_ENV}}/$BUILD_ENV/g" -e "s/{{CREATED_AT}}/$CREATED_AT/g" "$TEMPLATE_FILE" > "$OUTPUT_FILE"


echo "替换 BUILD_ENV 成功， 开始构建 Android APK... ➡ 正在构建 $BUILD_ENV 版本..."

./gradlew assembleRelease

echo "✅ Production APK 构建成功: $RELEASE_PATH"
# 将 .apk文件拷贝到指定目录并重命名为 demo-app-release.apk
mv "${RELEASE_PATH}"app-release.apk "${BUILD_PATH}"production-"${VERSION_NAME}"-"${VERSION_CODE}".apk

#====================================================================

#====================================================================
## 🔹 构建 demo 版本
BUILD_ENV="DEMO"

# 替换占位符并生成代码文件
sed -e "s/{{BUILD_ENV}}/$BUILD_ENV/g" -e "s/{{CREATED_AT}}/$CREATED_AT/g" "$TEMPLATE_FILE" > "$OUTPUT_FILE"


echo "替换 BUILD_ENV 成功， 开始构建 Android APK... ➡ 正在构建 $BUILD_ENV 版本..."

./gradlew assembleRelease

echo "✅ Demo APK 构建成功: $RELEASE_PATH"
# 将 .apk文件拷贝到指定目录并重命名为 demo-app-release.apk
mv "${RELEASE_PATH}"app-release.apk "${BUILD_PATH}"demo-"${VERSION_NAME}"-"${VERSION_CODE}".apk

#====================================================================


## 🔹 打开 Finder 并定位到 APK 目录
open "$BUILD_PATH"

echo "🎉 所有 APK 构建完成!"
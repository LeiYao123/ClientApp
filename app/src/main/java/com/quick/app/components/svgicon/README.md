## 利用 coil 加载本地 assets 内部的 svg

1. 添加依赖
    ```
    // app/build.gradle.kts
    implementation("io.coil-kt.coil3:coil-compose:3.1.0")
    implementation("io.coil-kt.coil3:coil-svg:3.1.0")
   ```
2. 将所需要的 svg 文件放入 assets 文件夹下
3. 利用 svg.sh 生成变量声明
4. 将变量声明 copy 进当前目录 config 文件内
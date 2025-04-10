#!/bin/bash


# 遍历icons目录下的所有svg文件
find icons -type f -name "*.svg" | while read -r file; do
    # 提取文件名（不带路径和扩展名）
    filename=$(basename "$file" .svg)

    # 生成变量名：替换-为_，并转为大写（可根据需求调整）
    var_name=$(echo "$filename" | sed 's/-/_/g')

    # 获取相对路径（从assets目录开始）
    relative_path="${file#assets/}"

    # 输出Kotlin常量格式
    echo "const val $var_name = \"$relative_path\""
done | sort  # 按字母顺序排序输出

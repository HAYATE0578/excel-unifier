# ExcelUnifier
![image](https://github.com/HAYATE0578/excel-unifier/assets/78299959/a5cc2eac-5ca4-4c1c-882b-d02922550417)

## At the begining/写在开始/はじめに

In the IT field in Japan, there are times when we have to deal with a large number of Excel files. In each review, we ultimately need to lock each sheet in the A1 cell, set the zoom to 100%, and display the first sheet. Although it's not mandatory, it's preferable to do so. To address this cumbersome task, I created a script in Java.  

在日本的IT现场工作，有时不得不处理大量的Excel文件。在一次次Review中，我们最后要把Excel的每张Sheet锁定在A1格子，让缩放为100，并且显示第一张Sheet。尽管这不是必须的，但最好这么做。为了解决这个繁琐的问题我用Java做了这个脚本。  

日本のIT現場では、大量のExcelファイルを扱うことがあります。レビューで、最終的には各シートをA1セルにロックし、ズームを100％に設定し、最初のシートを表示するのはおすすめです。必須ではないですが、やればましです。この手間のかかる課題に対処するために、私はJavaでスクリプトを作成しました。  

## How to use/用法/使い方
The ExcelUnifier.exe is a standalone executable file. When you drag and drop this file into a folder, then execute it to standardize the format of all Excel files in the current folder and its subfolders(recursion).  

ExcelUnifier.exe是可以单独执行的exe文件，把此文件拖入某个文件夹后执行，就可以把当前文件夹的所有Excel文件和子文件夹内的Excel文件的格式格式统一化(递归的)。  

ExcelUnifier.exeは単独で実行可能なファイルです。このファイルを特定のフォルダにドラッグして実行すると、そのフォルダ内のすべてのExcelファイルと子フォルダのExcel形式を統一化します。  

## Supported Excel Formats/可处理的Excel类型/処理できるExcel型
| Excel Type |
|-------|
| .xls|
| .xlsx |
| .xlsb |
| .xltx |
| .xltm |
| .xlt |
| .xlam |
| .xla |
| .xlw |
| .xlr |
| .xls |

## Tools/工具/ツール
+ Idea64
+ Apache POI
+ Jdk-21.0.1.12
+ Java Swing
+ ChatGpt 3.5

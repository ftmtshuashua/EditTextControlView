# EditTextControlView
EditTextView的控制器架构

>EditTextProcessor:EditText控制器

>ValueProcessor:内容范围控制器

>PasswordVisibilityProcessor:密码可见性控制器

>AndroidX 实现


## 使用
```
        //范围控制
        EditTextControlView v_1 = findViewById(R.id.view_1);
        v_1.addProcessor(new IntegerBorderProcessor(0, 10));

        //密码可见性控制
        View target = findViewById(R.id.view_2_1); //用于控制密码可见性的View
        EditTextControlView v_2 = findViewById(R.id.view_2);
        v_2.addProcessor(new PasswordVisibilityProcessor(target, PasswordVisibilityProcessor.Style.TOUCH));

        //....
```

## 配置依赖

在项目的build.gradle中添加
```
allprojects {
    repositories {
        maven { url 'https://www.jitpack.io' }
    }
}
```
在Model的build.gradle中添加 [![](https://jitpack.io/v/ftmtshuashua/EditTextControlView.svg)](https://jitpack.io/#ftmtshuashua/EditTextControlView)
```
dependencies {
    implementation 'com.github.ftmtshuashua:EditTextControlView:version'
}
```


## LICENSE

```
Copyright (c) 2018-present, EventChain Contributors.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```





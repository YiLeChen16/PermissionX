## 项目介绍

PermissionX 是一个用于简化Android项目中权限请求的一个轻量级权限请求框架，通过在界面中加入一个隐藏的Fragment，并将权限请求的 API 封装在其中，实现权限请求的简化。

## 如何依赖到该项目

通过添加如下配置就可将 PermissionX 引入你的项目啦~

### 1. gradle

#### Step 1.将 JitPack 存储库添加到您的根目录下的 settings.gradle 构建文件中

```css
	dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
	}
```

#### Step 2. 添加PermissionX依赖

```css
	dependencies {
	        implementation 'com.github.YiLeChen16:PermissionX:1.0.0'
	}
```

### 2.gradle.kts

#### Step 1. 将 JitPack 存储库添加到您的 settings.gradle.kts 构建文件中

```css
	dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url = uri("https://jitpack.io") }
		}
	}
```

#### Step 2. 添加 PermissionX 依赖

```css
	dependencies {
	        implementation("com.github.YiLeChen16:PermissionX:1.0.0")
	}
```

## 如何使用此框架

### 1. kotlin调用

```kotlin
//this通常是Activity对象
//kotlin调用其后可直接写多个要请求的权限，第二个参数为可变参数
PermissionX.request(this, Manifest.permission.CALL_PHONE,Manifest.permission.RECORD_AUDIO) { 
    allGranted, deniedList ->
                if (allGranted) {
                    Toast.makeText(this, "全部权限已申请成功！", Toast.LENGTH_SHORT).show()
                    //TODO::做你想做的事情吧~
                } else {
                    Toast.makeText(this, "您拒绝了 $deniedList 权限", Toast.LENGTH_SHORT).show()
                }
            }
```

### 2.  java 调用

```java
//this通常是Activity对象
//java调用需将多个权限封装为一个数组进行请求
PermissionX.INSTANCE.request(this, new String[]{Manifest.permission.CALL_PHONE, Manifest.permission.RECORD_AUDIO}, new Function2<Boolean, List<String>, Unit>() {
    @Override
    public Unit invoke(Boolean aBoolean, List<String> strings) {
        if (aBoolean) {
            Toast.makeText(SecondActivity.this, "全部权限已申请成功！", Toast.LENGTH_SHORT).show()
            //TODO::做你想做的事情吧~
        } else {
            String deniedPermissions = "您拒绝了 " + strings.toString() + " 权限";
            Toast.makeText(SecondActivity.this, deniedPermissions, Toast.LENGTH_SHORT).show();
        }
        return null;
    }
});
```


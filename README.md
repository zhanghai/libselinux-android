# libselinux-android

[![Android CI status](https://github.com/zhanghai/libselinux-android/workflows/Android%20CI/badge.svg)](https://github.com/zhanghai/libselinux-android/actions)

[`libselinux`](https://android.googlesource.com/platform/external/selinux/+/refs/heads/master/libselinux/) built with Android NDK, packaged as an Android library with some Java binding.

## Integration

Gradle:

```gradle
implementation 'me.zhanghai.android.libselinux:library:2.1.1'
```

## Usage

See [`SeLinux.java`](library/src/main/java/me/zhanghai/android/libselinux/SeLinux.java).

## License

    Copyright 2019 Hai Zhang

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

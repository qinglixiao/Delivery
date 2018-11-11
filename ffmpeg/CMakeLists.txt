# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.

add_library( # Sets the name of the library.
        native-lib

        # Sets the library as a shared library.
        SHARED

        # Provides a relative path to your source file(s).
        src/main/jni/native-lib.cpp src/main/jni/decode_audio.c src/main/jni/encode_audio.c)


find_library(log-lib
        log)

add_library(avutil-56
        SHARED
        IMPORTED)
set_target_properties(
        avutil-56
        PROPERTIES IMPORTED_LOCATION
        ${CMAKE_SOURCE_DIR}/libs/armeabi-v7a/libavutil-56.so
)

add_library(avcodec-58
        SHARED
        IMPORTED)
set_target_properties(
        avcodec-58
        PROPERTIES IMPORTED_LOCATION
        ${CMAKE_SOURCE_DIR}/libs/armeabi-v7a/libavcodec-58.so
)

add_library(swresample-3
        SHARED
        IMPORTED)
set_target_properties(
        swresample-3
        PROPERTIES IMPORTED_LOCATION
        ${CMAKE_SOURCE_DIR}/libs/armeabi-v7a/libswresample-3.so
)

include_directories(${CMAKE_SOURCE_DIR}/src/main/jni/include)

target_link_libraries(
        native-lib
        avutil-56
        avcodec-58
        swresample-3
        ${log-lib})

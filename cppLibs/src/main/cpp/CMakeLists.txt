#Cmake 最低要求版本
cmake_minimum_required(VERSION 3.10.2)

#链接路径下所有的cpp
file(GLOB allCpp *.cpp)

#添加lib
add_library(
        cppTest-native-lib
        SHARED
        ${allCpp}
)

find_library(
        log-lib
        log
)

#链接上面声明的库
target_link_libraries(
        cppTest-native-lib
        ${log-lib}
)
cmake_minimum_required(VERSION 3.4.1)

# https://android.googlesource.com/platform/external/pcre/+/refs/heads/master/Android.bp
add_library(pcre2 STATIC
        # libpcre2
        src/main/native/external/pcre/dist2/src/pcre2_auto_possess.c
        src/main/native/external/pcre/dist2/src/pcre2_compile.c
        src/main/native/external/pcre/dist2/src/pcre2_config.c
        src/main/native/external/pcre/dist2/src/pcre2_context.c
        src/main/native/external/pcre/dist2/src/pcre2_convert.c
        src/main/native/external/pcre/dist2/src/pcre2_dfa_match.c
        src/main/native/external/pcre/dist2/src/pcre2_error.c
        src/main/native/external/pcre/dist2/src/pcre2_extuni.c
        src/main/native/external/pcre/dist2/src/pcre2_find_bracket.c
        src/main/native/external/pcre/dist2/src/pcre2_maketables.c
        src/main/native/external/pcre/dist2/src/pcre2_match.c
        src/main/native/external/pcre/dist2/src/pcre2_match_data.c
        src/main/native/external/pcre/dist2/src/pcre2_jit_compile.c
        src/main/native/external/pcre/dist2/src/pcre2_newline.c
        src/main/native/external/pcre/dist2/src/pcre2_ord2utf.c
        src/main/native/external/pcre/dist2/src/pcre2_pattern_info.c
        src/main/native/external/pcre/dist2/src/pcre2_serialize.c
        src/main/native/external/pcre/dist2/src/pcre2_string_utils.c
        src/main/native/external/pcre/dist2/src/pcre2_study.c
        src/main/native/external/pcre/dist2/src/pcre2_substitute.c
        src/main/native/external/pcre/dist2/src/pcre2_substring.c
        src/main/native/external/pcre/dist2/src/pcre2_tables.c
        src/main/native/external/pcre/dist2/src/pcre2_ucd.c
        src/main/native/external/pcre/dist2/src/pcre2_valid_utf.c
        src/main/native/external/pcre/dist2/src/pcre2_xclass.c
        src/main/native/external/pcre/dist2/src/pcre2_chartables.c)
target_compile_options(pcre2
        PRIVATE
        # pcre_defaults
        -DHAVE_CONFIG_H
        -Wall
        -Werror)
target_include_directories(pcre2
        PRIVATE
        src/main/native/external/pcre/include_internal
        PUBLIC
        src/main/native/external/pcre/include)

# __fsetlocking needs __ANDROID_API__ >= 23, and it seems just an optimization, so just give it a
# no-op implementation to compile.
set(SELINUX_INIT_C_INPUT src/main/native/external/selinux/libselinux/src/init.c)
set(SELINUX_INIT_C_OUTPUT "${CMAKE_CURRENT_BINARY_DIR}/init_patched.c")
set(SELINUX_INIT_C_PATCH src/main/native/init.c.patch)
set(CMAKE_MODULE_PATH ${CMAKE_MODULE_PATH} "${CMAKE_CURRENT_SOURCE_DIR}/cmake")
find_package(Patch REQUIRED)
add_custom_command(OUTPUT "${SELINUX_INIT_C_OUTPUT}"
        COMMAND "${CMAKE_COMMAND}" -E copy "${SELINUX_INIT_C_INPUT}" "${SELINUX_INIT_C_OUTPUT}"
        COMMAND "${Patch_EXECUTABLE}" "${SELINUX_INIT_C_OUTPUT}" "${SELINUX_INIT_C_PATCH}"
        MAIN_DEPENDENCY "${SELINUX_INIT_C_INPUT}"
        DEPENDS "${SELINUX_INIT_C_PATCH}"
        WORKING_DIRECTORY ${CMAKE_CURRENT_SOURCE_DIR}
        COMMENT "Generating init_patched.c"
        VERBATIM)

# https://android.googlesource.com/platform/external/selinux/+/refs/heads/master/libselinux/Android.bp
add_library(selinux STATIC
        # libselinux_defaults
        src/main/native/external/selinux/libselinux/src/booleans.c
        src/main/native/external/selinux/libselinux/src/callbacks.c
        src/main/native/external/selinux/libselinux/src/freecon.c
        src/main/native/external/selinux/libselinux/src/label_backends_android.c
        src/main/native/external/selinux/libselinux/src/label.c
        src/main/native/external/selinux/libselinux/src/label_support.c
        src/main/native/external/selinux/libselinux/src/matchpathcon.c
        src/main/native/external/selinux/libselinux/src/setrans_client.c
        src/main/native/external/selinux/libselinux/src/sha1.c
        # libselinux
        src/main/native/external/selinux/libselinux/src/label_file.c
        src/main/native/external/selinux/libselinux/src/regex.c
        # linux_glibc
        src/main/native/external/selinux/libselinux/src/avc.c
        src/main/native/external/selinux/libselinux/src/avc_internal.c
        src/main/native/external/selinux/libselinux/src/avc_sidtab.c
        src/main/native/external/selinux/libselinux/src/compute_av.c
        src/main/native/external/selinux/libselinux/src/compute_create.c
        src/main/native/external/selinux/libselinux/src/compute_member.c
        src/main/native/external/selinux/libselinux/src/context.c
        src/main/native/external/selinux/libselinux/src/enabled.c
        src/main/native/external/selinux/libselinux/src/fgetfilecon.c
        src/main/native/external/selinux/libselinux/src/getenforce.c
        src/main/native/external/selinux/libselinux/src/getfilecon.c
        src/main/native/external/selinux/libselinux/src/get_initial_context.c
        #src/main/native/external/selinux/libselinux/src/init.c
        "${SELINUX_INIT_C_OUTPUT}"
        src/main/native/external/selinux/libselinux/src/lgetfilecon.c
        src/main/native/external/selinux/libselinux/src/load_policy.c
        src/main/native/external/selinux/libselinux/src/lsetfilecon.c
        src/main/native/external/selinux/libselinux/src/mapping.c
        src/main/native/external/selinux/libselinux/src/procattr.c
        src/main/native/external/selinux/libselinux/src/setenforce.c
        src/main/native/external/selinux/libselinux/src/setexecfilecon.c
        src/main/native/external/selinux/libselinux/src/setfilecon.c
        src/main/native/external/selinux/libselinux/src/stringrep.c
        # Added for this library
        src/main/native/external/selinux/libselinux/src/fsetfilecon.c)
target_compile_options(selinux
        PRIVATE
        # libselinux_defaults
        -DNO_PERSISTENTLY_STORED_PATTERNS
        -DDISABLE_SETRANS
        -DDISABLE_BOOL
        -D_GNU_SOURCE
        -DNO_MEDIA_BACKEND
        -DNO_X_BACKEND
        -DNO_DB_BACKEND
        -Wall
        -Werror
        -Wno-error=missing-noreturn
        -Wno-error=unused-function
        -Wno-error=unused-variable
        # libselinux
        -DUSE_PCRE2)
target_include_directories(selinux
        PUBLIC
        src/main/native/external/selinux/libselinux/include
        # Hack for init_patched.c including local files.
        PRIVATE
        src/main/native/external/selinux/libselinux/src)
target_link_libraries(selinux
        PRIVATE
        pcre2)

find_library(LOG_LIBRARY log)
add_library(selinux-jni SHARED src/main/native/libselinux-jni.c)
target_link_libraries(selinux-jni selinux ${LOG_LIBRARY})

# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.5

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list


# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /usr/local/Cellar/cmake/3.5.2/bin/cmake

# The command to remove a file.
RM = /usr/local/Cellar/cmake/3.5.2/bin/cmake -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /Users/BaoHan/desktop/DT/minion-1.8

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /Users/BaoHan/desktop/DT/minion-1.8/bin

# Utility rule file for htmlhelp.

# Include the progress variables for this target.
include CMakeFiles/htmlhelp.dir/progress.make

CMakeFiles/htmlhelp:
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --blue --bold --progress-dir=/Users/BaoHan/desktop/DT/minion-1.8/bin/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) htmlhelp
	cd /Users/BaoHan/desktop/DT/minion-1.8 && /bin/bash docs/genhelp/genhelp.sh minion

htmlhelp: CMakeFiles/htmlhelp
htmlhelp: CMakeFiles/htmlhelp.dir/build.make

.PHONY : htmlhelp

# Rule to build all files generated by this target.
CMakeFiles/htmlhelp.dir/build: htmlhelp

.PHONY : CMakeFiles/htmlhelp.dir/build

CMakeFiles/htmlhelp.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/htmlhelp.dir/cmake_clean.cmake
.PHONY : CMakeFiles/htmlhelp.dir/clean

CMakeFiles/htmlhelp.dir/depend:
	cd /Users/BaoHan/desktop/DT/minion-1.8/bin && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /Users/BaoHan/desktop/DT/minion-1.8 /Users/BaoHan/desktop/DT/minion-1.8 /Users/BaoHan/desktop/DT/minion-1.8/bin /Users/BaoHan/desktop/DT/minion-1.8/bin /Users/BaoHan/desktop/DT/minion-1.8/bin/CMakeFiles/htmlhelp.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/htmlhelp.dir/depend


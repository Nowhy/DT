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

# Utility rule file for test-random-quick.

# Include the progress variables for this target.
include CMakeFiles/test-random-quick.dir/progress.make

CMakeFiles/test-random-quick: minion
	cd /Users/BaoHan/desktop/DT/minion-1.8 && ./mini-scripts/testallconstraints.py --numtests=25 --procs=2 --minion=/Users/BaoHan/desktop/DT/minion-1.8/bin/minion

test-random-quick: CMakeFiles/test-random-quick
test-random-quick: CMakeFiles/test-random-quick.dir/build.make

.PHONY : test-random-quick

# Rule to build all files generated by this target.
CMakeFiles/test-random-quick.dir/build: test-random-quick

.PHONY : CMakeFiles/test-random-quick.dir/build

CMakeFiles/test-random-quick.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/test-random-quick.dir/cmake_clean.cmake
.PHONY : CMakeFiles/test-random-quick.dir/clean

CMakeFiles/test-random-quick.dir/depend:
	cd /Users/BaoHan/desktop/DT/minion-1.8/bin && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /Users/BaoHan/desktop/DT/minion-1.8 /Users/BaoHan/desktop/DT/minion-1.8 /Users/BaoHan/desktop/DT/minion-1.8/bin /Users/BaoHan/desktop/DT/minion-1.8/bin /Users/BaoHan/desktop/DT/minion-1.8/bin/CMakeFiles/test-random-quick.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/test-random-quick.dir/depend


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

# Utility rule file for generate.

# Include the progress variables for this target.
include CMakeFiles/generate.dir/progress.make

CMakeFiles/generate: bibd
CMakeFiles/generate: golomb
CMakeFiles/generate: graceful
CMakeFiles/generate: indicator
CMakeFiles/generate: langford
CMakeFiles/generate: nqueens
CMakeFiles/generate: primequeens
CMakeFiles/generate: solitaire
CMakeFiles/generate: steelmill
CMakeFiles/generate: sports


generate: CMakeFiles/generate
generate: CMakeFiles/generate.dir/build.make

.PHONY : generate

# Rule to build all files generated by this target.
CMakeFiles/generate.dir/build: generate

.PHONY : CMakeFiles/generate.dir/build

CMakeFiles/generate.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/generate.dir/cmake_clean.cmake
.PHONY : CMakeFiles/generate.dir/clean

CMakeFiles/generate.dir/depend:
	cd /Users/BaoHan/desktop/DT/minion-1.8/bin && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /Users/BaoHan/desktop/DT/minion-1.8 /Users/BaoHan/desktop/DT/minion-1.8 /Users/BaoHan/desktop/DT/minion-1.8/bin /Users/BaoHan/desktop/DT/minion-1.8/bin /Users/BaoHan/desktop/DT/minion-1.8/bin/CMakeFiles/generate.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/generate.dir/depend

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

# Include any dependencies generated for this target.
include CMakeFiles/steelmill.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/steelmill.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/steelmill.dir/flags.make

CMakeFiles/steelmill.dir/generators/Steelmill/steelmill-solver.o: CMakeFiles/steelmill.dir/flags.make
CMakeFiles/steelmill.dir/generators/Steelmill/steelmill-solver.o: ../generators/Steelmill/steelmill-solver.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/Users/BaoHan/desktop/DT/minion-1.8/bin/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/steelmill.dir/generators/Steelmill/steelmill-solver.o"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++   $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/steelmill.dir/generators/Steelmill/steelmill-solver.o -c /Users/BaoHan/desktop/DT/minion-1.8/generators/Steelmill/steelmill-solver.cpp

CMakeFiles/steelmill.dir/generators/Steelmill/steelmill-solver.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/steelmill.dir/generators/Steelmill/steelmill-solver.i"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /Users/BaoHan/desktop/DT/minion-1.8/generators/Steelmill/steelmill-solver.cpp > CMakeFiles/steelmill.dir/generators/Steelmill/steelmill-solver.i

CMakeFiles/steelmill.dir/generators/Steelmill/steelmill-solver.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/steelmill.dir/generators/Steelmill/steelmill-solver.s"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /Users/BaoHan/desktop/DT/minion-1.8/generators/Steelmill/steelmill-solver.cpp -o CMakeFiles/steelmill.dir/generators/Steelmill/steelmill-solver.s

CMakeFiles/steelmill.dir/generators/Steelmill/steelmill-solver.o.requires:

.PHONY : CMakeFiles/steelmill.dir/generators/Steelmill/steelmill-solver.o.requires

CMakeFiles/steelmill.dir/generators/Steelmill/steelmill-solver.o.provides: CMakeFiles/steelmill.dir/generators/Steelmill/steelmill-solver.o.requires
	$(MAKE) -f CMakeFiles/steelmill.dir/build.make CMakeFiles/steelmill.dir/generators/Steelmill/steelmill-solver.o.provides.build
.PHONY : CMakeFiles/steelmill.dir/generators/Steelmill/steelmill-solver.o.provides

CMakeFiles/steelmill.dir/generators/Steelmill/steelmill-solver.o.provides.build: CMakeFiles/steelmill.dir/generators/Steelmill/steelmill-solver.o


# Object files for target steelmill
steelmill_OBJECTS = \
"CMakeFiles/steelmill.dir/generators/Steelmill/steelmill-solver.o"

# External object files for target steelmill
steelmill_EXTERNAL_OBJECTS =

steelmill: CMakeFiles/steelmill.dir/generators/Steelmill/steelmill-solver.o
steelmill: CMakeFiles/steelmill.dir/build.make
steelmill: CMakeFiles/steelmill.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/Users/BaoHan/desktop/DT/minion-1.8/bin/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX executable steelmill"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/steelmill.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/steelmill.dir/build: steelmill

.PHONY : CMakeFiles/steelmill.dir/build

CMakeFiles/steelmill.dir/requires: CMakeFiles/steelmill.dir/generators/Steelmill/steelmill-solver.o.requires

.PHONY : CMakeFiles/steelmill.dir/requires

CMakeFiles/steelmill.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/steelmill.dir/cmake_clean.cmake
.PHONY : CMakeFiles/steelmill.dir/clean

CMakeFiles/steelmill.dir/depend:
	cd /Users/BaoHan/desktop/DT/minion-1.8/bin && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /Users/BaoHan/desktop/DT/minion-1.8 /Users/BaoHan/desktop/DT/minion-1.8 /Users/BaoHan/desktop/DT/minion-1.8/bin /Users/BaoHan/desktop/DT/minion-1.8/bin /Users/BaoHan/desktop/DT/minion-1.8/bin/CMakeFiles/steelmill.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/steelmill.dir/depend


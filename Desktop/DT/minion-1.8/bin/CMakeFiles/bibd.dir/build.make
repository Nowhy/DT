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
include CMakeFiles/bibd.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/bibd.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/bibd.dir/flags.make

CMakeFiles/bibd.dir/generators/Bibd/MinionBIBDInstanceGenerator.o: CMakeFiles/bibd.dir/flags.make
CMakeFiles/bibd.dir/generators/Bibd/MinionBIBDInstanceGenerator.o: ../generators/Bibd/MinionBIBDInstanceGenerator.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/Users/BaoHan/desktop/DT/minion-1.8/bin/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/bibd.dir/generators/Bibd/MinionBIBDInstanceGenerator.o"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++   $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/bibd.dir/generators/Bibd/MinionBIBDInstanceGenerator.o -c /Users/BaoHan/desktop/DT/minion-1.8/generators/Bibd/MinionBIBDInstanceGenerator.cpp

CMakeFiles/bibd.dir/generators/Bibd/MinionBIBDInstanceGenerator.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/bibd.dir/generators/Bibd/MinionBIBDInstanceGenerator.i"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /Users/BaoHan/desktop/DT/minion-1.8/generators/Bibd/MinionBIBDInstanceGenerator.cpp > CMakeFiles/bibd.dir/generators/Bibd/MinionBIBDInstanceGenerator.i

CMakeFiles/bibd.dir/generators/Bibd/MinionBIBDInstanceGenerator.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/bibd.dir/generators/Bibd/MinionBIBDInstanceGenerator.s"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /Users/BaoHan/desktop/DT/minion-1.8/generators/Bibd/MinionBIBDInstanceGenerator.cpp -o CMakeFiles/bibd.dir/generators/Bibd/MinionBIBDInstanceGenerator.s

CMakeFiles/bibd.dir/generators/Bibd/MinionBIBDInstanceGenerator.o.requires:

.PHONY : CMakeFiles/bibd.dir/generators/Bibd/MinionBIBDInstanceGenerator.o.requires

CMakeFiles/bibd.dir/generators/Bibd/MinionBIBDInstanceGenerator.o.provides: CMakeFiles/bibd.dir/generators/Bibd/MinionBIBDInstanceGenerator.o.requires
	$(MAKE) -f CMakeFiles/bibd.dir/build.make CMakeFiles/bibd.dir/generators/Bibd/MinionBIBDInstanceGenerator.o.provides.build
.PHONY : CMakeFiles/bibd.dir/generators/Bibd/MinionBIBDInstanceGenerator.o.provides

CMakeFiles/bibd.dir/generators/Bibd/MinionBIBDInstanceGenerator.o.provides.build: CMakeFiles/bibd.dir/generators/Bibd/MinionBIBDInstanceGenerator.o


# Object files for target bibd
bibd_OBJECTS = \
"CMakeFiles/bibd.dir/generators/Bibd/MinionBIBDInstanceGenerator.o"

# External object files for target bibd
bibd_EXTERNAL_OBJECTS =

bibd: CMakeFiles/bibd.dir/generators/Bibd/MinionBIBDInstanceGenerator.o
bibd: CMakeFiles/bibd.dir/build.make
bibd: CMakeFiles/bibd.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/Users/BaoHan/desktop/DT/minion-1.8/bin/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX executable bibd"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/bibd.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/bibd.dir/build: bibd

.PHONY : CMakeFiles/bibd.dir/build

CMakeFiles/bibd.dir/requires: CMakeFiles/bibd.dir/generators/Bibd/MinionBIBDInstanceGenerator.o.requires

.PHONY : CMakeFiles/bibd.dir/requires

CMakeFiles/bibd.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/bibd.dir/cmake_clean.cmake
.PHONY : CMakeFiles/bibd.dir/clean

CMakeFiles/bibd.dir/depend:
	cd /Users/BaoHan/desktop/DT/minion-1.8/bin && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /Users/BaoHan/desktop/DT/minion-1.8 /Users/BaoHan/desktop/DT/minion-1.8 /Users/BaoHan/desktop/DT/minion-1.8/bin /Users/BaoHan/desktop/DT/minion-1.8/bin /Users/BaoHan/desktop/DT/minion-1.8/bin/CMakeFiles/bibd.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/bibd.dir/depend


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
include CMakeFiles/sports.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/sports.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/sports.dir/flags.make

CMakeFiles/sports.dir/generators/SportsSchedule/MinionSportsInstanceGenerator.o: CMakeFiles/sports.dir/flags.make
CMakeFiles/sports.dir/generators/SportsSchedule/MinionSportsInstanceGenerator.o: ../generators/SportsSchedule/MinionSportsInstanceGenerator.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/Users/BaoHan/desktop/DT/minion-1.8/bin/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/sports.dir/generators/SportsSchedule/MinionSportsInstanceGenerator.o"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++   $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/sports.dir/generators/SportsSchedule/MinionSportsInstanceGenerator.o -c /Users/BaoHan/desktop/DT/minion-1.8/generators/SportsSchedule/MinionSportsInstanceGenerator.cpp

CMakeFiles/sports.dir/generators/SportsSchedule/MinionSportsInstanceGenerator.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/sports.dir/generators/SportsSchedule/MinionSportsInstanceGenerator.i"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /Users/BaoHan/desktop/DT/minion-1.8/generators/SportsSchedule/MinionSportsInstanceGenerator.cpp > CMakeFiles/sports.dir/generators/SportsSchedule/MinionSportsInstanceGenerator.i

CMakeFiles/sports.dir/generators/SportsSchedule/MinionSportsInstanceGenerator.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/sports.dir/generators/SportsSchedule/MinionSportsInstanceGenerator.s"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /Users/BaoHan/desktop/DT/minion-1.8/generators/SportsSchedule/MinionSportsInstanceGenerator.cpp -o CMakeFiles/sports.dir/generators/SportsSchedule/MinionSportsInstanceGenerator.s

CMakeFiles/sports.dir/generators/SportsSchedule/MinionSportsInstanceGenerator.o.requires:

.PHONY : CMakeFiles/sports.dir/generators/SportsSchedule/MinionSportsInstanceGenerator.o.requires

CMakeFiles/sports.dir/generators/SportsSchedule/MinionSportsInstanceGenerator.o.provides: CMakeFiles/sports.dir/generators/SportsSchedule/MinionSportsInstanceGenerator.o.requires
	$(MAKE) -f CMakeFiles/sports.dir/build.make CMakeFiles/sports.dir/generators/SportsSchedule/MinionSportsInstanceGenerator.o.provides.build
.PHONY : CMakeFiles/sports.dir/generators/SportsSchedule/MinionSportsInstanceGenerator.o.provides

CMakeFiles/sports.dir/generators/SportsSchedule/MinionSportsInstanceGenerator.o.provides.build: CMakeFiles/sports.dir/generators/SportsSchedule/MinionSportsInstanceGenerator.o


# Object files for target sports
sports_OBJECTS = \
"CMakeFiles/sports.dir/generators/SportsSchedule/MinionSportsInstanceGenerator.o"

# External object files for target sports
sports_EXTERNAL_OBJECTS =

sports: CMakeFiles/sports.dir/generators/SportsSchedule/MinionSportsInstanceGenerator.o
sports: CMakeFiles/sports.dir/build.make
sports: CMakeFiles/sports.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/Users/BaoHan/desktop/DT/minion-1.8/bin/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX executable sports"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/sports.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/sports.dir/build: sports

.PHONY : CMakeFiles/sports.dir/build

CMakeFiles/sports.dir/requires: CMakeFiles/sports.dir/generators/SportsSchedule/MinionSportsInstanceGenerator.o.requires

.PHONY : CMakeFiles/sports.dir/requires

CMakeFiles/sports.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/sports.dir/cmake_clean.cmake
.PHONY : CMakeFiles/sports.dir/clean

CMakeFiles/sports.dir/depend:
	cd /Users/BaoHan/desktop/DT/minion-1.8/bin && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /Users/BaoHan/desktop/DT/minion-1.8 /Users/BaoHan/desktop/DT/minion-1.8 /Users/BaoHan/desktop/DT/minion-1.8/bin /Users/BaoHan/desktop/DT/minion-1.8/bin /Users/BaoHan/desktop/DT/minion-1.8/bin/CMakeFiles/sports.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/sports.dir/depend

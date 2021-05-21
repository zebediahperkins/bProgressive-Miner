# [bProgressive-Miner](https://community.tribot.org/topic/81450-bprogressive-miner-open-source/)
bProgressive-Miner is a task-based mining script for Tribot. This script should work anywhere on any rock in the game.
## Progressive Mining
bProgressive-miner uses a task system to decide what to do, how to do it, and when. The user sends the script 
[ProgressiveTasks](https://github.com/zebediahperkins/bProgressive-Miner/blob/main/scripts/progressive-miner/src/scripts/task/ProgressiveTask.java) 
through the javafx gui. These tasks can be saved and loaded at a later time for the user's convenience.
## Anti-Ban
This script implements Tribot's Anti-Ban compliance
through the [AntiBan](https://github.com/zebediahperkins/bProgressive-Miner/blob/main/libraries/bApi/src/scripts/antiban/AntiBan.java)
singleton class in bApi.
## Profiles
Profiles are arrays of ProgressiveTasks stored as JSON objects in the user's .tribot folder.
They can be used to save/load user settings at the click of a button.

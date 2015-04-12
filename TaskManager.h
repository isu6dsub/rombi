#pragma once

class TaskManager
{
public:
	static TaskManager* get();

	void updateCurrentTask();
	Task* currentTask();
private:
	TaskManager();
	static TaskManager* sInstance;
};


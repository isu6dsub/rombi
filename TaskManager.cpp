#include "TaskManager.h"

TaskManager* TaskManager::sInstance = NULL;

TaskManager* TaskManager::get()
{
	if(sInstance == NULL)
		sInstance = new TaskManager();
	return sInstance;
}

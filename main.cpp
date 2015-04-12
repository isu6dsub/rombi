
int main(int argc, const char* argv[])
{

	while(true)
	{
		updateInput();
		TaskManager::get()->updateCurrentTask();
		flushOutput();
	}
}


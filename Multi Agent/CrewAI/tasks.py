from crewai import Task

class Tasks:
    def features_task(self, agent, website):
        return Task(
            description= 'Generate a list of features the given website will need. Website: ' + website,
            expected_output= 'A list of useful features the given website should have.',
            async_execution=True,
            agent=agent
        )

    def name_task(self, agent, website):
        return Task(
            description= 'Generate a name for the given website. Make it unique and catchy. Website: ' + website,
            expected_output= 'A name for the given website.',
            async_execution=True,
            agent=agent
        )
    
    def develop_task(self, agent, website):
        return Task(
            description= 'Generate a pretty Flask website with the derived name and features. Website: ' + website + '. Name: {name}. Features: {features}.',
            expected_output= 'A pretty Flask website with the given name and features.',
            async_execution=False,
            agent=agent
        )


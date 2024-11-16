from crewai import Agent

class Agents:
    def features_agent(self):
        return Agent(
            role='Website Planner',
            goal='Generate useful features a given website would need',
            backstory='As a Website Planner, your mission is to create a list of features that the given website will need. Your list will be implemented into the development of the given website.',
            verbose=True	#For logging
        )

    def naming_agent(self):
        return Agent(
            role='Website Namer',
            goal='Generate a fitting name for a given website',
            backstory='As a Website Namer, your mission is to create a name for a given website. Your name will be the name of the given website.',
            verbose=True	#For logging
        )
    
    def developer_agent(self):
        return Agent(
            role='Website Developer',
            goal='Generate a Flask website with the provided features and name.',
            backstory='As a Website Developer, your mission is to create a Flask website that has the given features and is called the given website name.',
            verbose=True	#For logging
        )

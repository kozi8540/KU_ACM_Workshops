from crewai import Crew, Process
import os

# Import Agent and Task classes
from agents import Agents
from tasks import Tasks

# Set OpenAI API key
os.environ['OPENAI_API_KEY'] = ''
os.environ['OPENAI_MODEL_NAME'] = 'gpt-4o'

# Input
website = 'to-do list organizer'

# Get agents
agents = Agents()
features_agent = agents.features_agent()
naming_agent = agents.naming_agent()
developer_agent = agents.developer_agent()
my_agents = [features_agent, naming_agent, developer_agent]
             
# Get tasks
tasks = Tasks()
my_tasks = [tasks.features_task(features_agent, website), tasks.name_task(naming_agent, website), tasks.develop_task(developer_agent, website)]

crew = Crew(
  agents=my_agents,
  tasks=my_tasks,
  	process=Process.sequential
)
result = crew.kickoff()
print(result)
from typing import TypedDict
from langgraph.graph import StateGraph, END
from openai import OpenAI

# Class for storing and sharing data between nodes
class GraphState(TypedDict):
    website: str = ''
    name: str = ''
    features: str = ''
    output: str = ''

# Get website features node
def get_features(state):
    # Get input from graph state
    website = state['website']  

    # Generate website features with ChatGPT
    features = client.chat.completions.create(
        model="gpt-3.5-turbo",
        messages=[
            {"role": "system", "content": "You are a website planner that lists features a given website would need."},
            {"role": "user", "content":"Give me features the following website would need:" + website},
        ]
    ).choices[0].message.content

    # Update graph state with generated features
    return {'features': features}

# Get website name node
def get_name(state):
    # Get input from graph state
    website = state['website']  

    # Generate website name with ChatGPT
    name = client.chat.completions.create(
        model="gpt-3.5-turbo",
        messages=[
            {"role": "system", "content": "You are a website namer that names a given website."},
            {"role": "user", "content":"Give me name for the following website:" + website},
        ]
    ).choices[0].message.content

    # Update graph state with generated name
    return {'name': name}

# Get website code node
def develop_website(state):
    # Get input from graph state
    website = state['website']  
    name = state['name']
    features = state['features']

    # Generate website code with ChatGPT
    output = client.chat.completions.create(
        model="gpt-4o",
        messages=[
            {"role": "system", "content": "You are a website developer that writes a pretty Flask web app for a given website with the given name and features."},
            {"role": "user", "content":f"Generate code for the given website in Flask with the given name and features. Make the website pretty and include the html code\n website: {website}\n name: {website}\n features: {features}" },
        ]
    ).choices[0].message.content

    # Update graph state with generated code
    return {'output': output}


# Init graph and OpenAI client
workflow = StateGraph(GraphState)
client = OpenAI(api_key= '')

# Add nodes to the graph
workflow.add_node("get_features", get_features)
workflow.add_node("get_name", get_name)
workflow.add_node("develop_website", develop_website)

# Add edges to the graph to connect the nodes
workflow.set_entry_point("get_features")
workflow.add_edge("get_features", "get_name")
workflow.add_edge("get_name", "develop_website")
workflow.add_edge("develop_website", END)

# Run the graph
app = workflow.compile()
inputs = {"website": "To-do list organizer"}
result = app.invoke(inputs)
print(result['output'])
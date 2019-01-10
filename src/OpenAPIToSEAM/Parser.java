package OpenAPIToSEAM;

import OpenAPIToSEAM.parser.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser extends Generator {

    private static Map<String, String> repertoryDependencies = new HashMap<>();
    private static Map<String, String> repertoryTagsToIdProcess = new HashMap<>();
    private static Map<String, String> repertoryTagsToIdTags = new HashMap<>();
    private static Map<String, String> newCompositeObject = new HashMap<>();
    private static Map<String, String> newCompositeObjectProcess = new HashMap<>();

    private static String mainObjectID = "";
    private static String mainProcessID = "";

    /**
     * Parse the "info:" indentation level of the input
     * @param info to be parsed
     */
    public static void genTitle(Object info) {
        //Generate an ID for the main working object and it's process
        mainObjectID = Utils.genID();
        mainProcessID = Utils.genID();

        //Parse the input to gather information from the info part of the input
        Information information = new Information(info);

        //Get the name of the main Object and generates its string
        String nodeMainObject = Nodes.genNodesWorkingObjectComposite(information.getTitle());
        nodeMainObject = String.format(Nodes.genNodeStructureData(mainObjectID), nodeMainObject);

        //Get the name of the main Process and generates its string
        String nodeMainProcess = Nodes.genNodesDitributedAction(information.getTitle());
        nodeMainProcess = String.format(Nodes.genNodeStructureData(mainProcessID), nodeMainProcess);

        //Add the new nodes to the current data
        concatNodesData(nodeMainObject);
        concatNodesData(nodeMainProcess);

        //Create an edge between the main object and the main process
        String edgeMainProcessToMainObject = Utils.genID();
        String edge = String.format(Edges.genEdgeStructureData(edgeMainProcessToMainObject), Edges.genEdgesInclude(mainObjectID, mainProcessID));

        //Add the edge to the current data
        concatEdgesData(edge);

        //Add the newly created nodes/edges to the scenario
        concatNodesScenarios(Utils.createSpace(4) + Nodes.genNodeStructureScenarios(mainObjectID));
        concatNodesScenarios(Utils.createSpace(4) + Nodes.genNodeStructureScenarios(mainProcessID));
        concatEdgesScenarios(Utils.createSpace(4) + Edges.genEdgeStructureScenario(edgeMainProcessToMainObject));
    }


    /**
     * Parse the "tags:" indentation level of the input
     * @param tags to be parsed
     */
    public static void genTags(Object tags, Object dependencies) {

        //Parse the input to gather information from the tags part of the input
        Tags t = new Tags(tags);

        fillProcessesDependencies(dependencies);

        //Creates a tag object for each Tag with its process included
        for(int i = 0; i < t.getTags().size(); i++){
            //generate an ID for the current Tag and it's corresponding process
            String currentTagObjectID = Utils.genID();
            String currentTagProcessID = Utils.genID();

            repertoryTagsToIdTags.put(t.getTags().get(i), currentTagObjectID);
            repertoryTagsToIdProcess.put(t.getTags().get(i), currentTagProcessID);

            //Create the Tag object node using the Tag name
            String nodeTagObject = Nodes.genNodesWorkingObject(t.getTags().get(i));
            nodeTagObject = String.format(Nodes.genNodeStructureData(currentTagObjectID), nodeTagObject);

            //Create the Tag process node
            String nodeTagProcess = Nodes.genNodesLocalisedAction(t.getTags().get(i));
            nodeTagProcess = String.format(Nodes.genNodeStructureData(currentTagProcessID), nodeTagProcess);

            //Add the new nodes to the current data
            concatNodesData(nodeTagObject);
            concatNodesData(nodeTagProcess);

            //Create an edge between the Tag object and its corresponding process
            String edgeTagObjectToProcessID = Utils.genID();
            String edgeTagObjectToProcess = String.format(Edges.genEdgeStructureData(edgeTagObjectToProcessID), Edges.genEdgesInclude(currentTagObjectID, currentTagProcessID));

            //Add the new edge to the current data
            concatEdgesData(edgeTagObjectToProcess);

            //Add the newly created nodes/edges to the scenario
            concatNodesScenarios(Utils.createSpace(4) + Nodes.genNodeStructureScenarios(currentTagObjectID));
            concatNodesScenarios(Utils.createSpace(4) + Nodes.genNodeStructureScenarios(currentTagProcessID));
            concatEdgesScenarios(Utils.createSpace(4) + Edges.genEdgeStructureScenario(edgeTagObjectToProcessID));
        }
    }

    /**
     * Fill dependencies map
     * @param dependencies
     */
    private static void fillProcessesDependencies(Object dependencies) {
        Dependencies d = new Dependencies(dependencies);


        for(Map.Entry<String, List<Map<String, String>>> tags : d.getDependencies().entrySet()) {
            for (Map<String, String> attributes : tags.getValue()) {

                //Handle processes relationship
                if (attributes.get("name") != null) {
                    //If the current tag is contained in a process (other than the main), add it to the repository
                    repertoryDependencies.put(tags.getKey(), attributes.get("name"));
                }
            }
        }
    }

    /**
     * Parse the "paths:" indentation level of the input
     * @param paths to be parsed
     */
    public static void genPaths(Object paths, Object component) throws IllegalArgumentException {

        //Parse the input to gather information from the paths and component part of the input
        Paths path = new Paths(paths);
        Component c = new Component(component);

        //Generates nodes/edges for every different paths
        for(Map.Entry<String, Queries> elem : path.getPaths().entrySet()){

            //For a path, handles every POST/GET/PUT query
            for(Query q : elem.getValue().getQueries()){
                String currentQueryID = Utils.genID();
                String queryParameterInID = Utils.genID();
                String queryParameterOutID = Utils.genID();
                String edgeTagToQueryID = Utils.genID();
                String edgeQueryToParameterInID = Utils.genID();
                String edgeQueryToParameterOutID = Utils.genID();
                String edgeParameterInToParameterOutID = Utils.genID();

                //Generates a node for the current query action
                String node = Nodes.genNodesLocalisedActionStereotype(q.getType().toUpperCase(), q.getDescription());
                node = String.format(Nodes.genNodeStructureData(currentQueryID), node);

                //Generates the node for the "in" parameter of the current query
                String queryParameterIn = Utils.createSpace(4) + q.genIO(c, "in");
                queryParameterIn = String.format(Nodes.genNodeStructureData(queryParameterInID), queryParameterIn);

                //Generates the node for the "out" parameter of the current query
                String queryParameterOut = Utils.createSpace(4) + q.genIO(c, "out");
                queryParameterOut = String.format(Nodes.genNodeStructureData(queryParameterOutID), queryParameterOut);

                //Add the new nodes to the current data
                concatNodesData(node);
                concatNodesData(queryParameterIn);
                concatNodesData(queryParameterOut);

                //Create an edge between the query and the "in" parameter
                String edgeQueryToParameterIn = String.format(Edges.genEdgeStructureData(edgeQueryToParameterInID), Edges.genEdgesInclude(currentQueryID, queryParameterInID));
                concatEdgesData(edgeQueryToParameterIn);

                //Create an edge between the query and the "out" parameter
                String edgeQueryToParameterOut = String.format(Edges.genEdgeStructureData(edgeQueryToParameterOutID), Edges.genEdgesInclude(currentQueryID, queryParameterOutID));
                concatEdgesData(edgeQueryToParameterOut);

                //Create an edge between the "in" parameter and the "out" parameter
                String edgeParameterInToParameterOut = String.format(Edges.genEdgeStructureData(edgeParameterInToParameterOutID), Edges.genEdgesBetweenParameters(queryParameterInID, queryParameterOutID, elem.getKey()));
                concatEdgesData(edgeParameterInToParameterOut);

                //Create an edge between the current query and the corresponding tag
                String edgeQToTag = String.format(Edges.genEdgeStructureData(edgeTagToQueryID), Edges.genEdgesInclude(repertoryTagsToIdProcess.get(q.getTags().get(0)), currentQueryID));
                concatEdgesData(edgeQToTag);

                //Add the newly created nodes/edges to the scenario
                concatNodesScenarios(Utils.createSpace(4) + Nodes.genNodeStructureScenarios(currentQueryID));
                concatNodesScenarios(Utils.createSpace(4) + Nodes.genNodeStructureScenarios(queryParameterInID));
                concatNodesScenarios(Utils.createSpace(4) + Nodes.genNodeStructureScenarios(queryParameterOutID));
                concatEdgesScenarios(Utils.createSpace(4) + Edges.genEdgeStructureScenario(edgeTagToQueryID));
                concatEdgesScenarios(Utils.createSpace(4) + Edges.genEdgeStructureScenario(edgeQueryToParameterInID));
                concatEdgesScenarios(Utils.createSpace(4) + Edges.genEdgeStructureScenario(edgeQueryToParameterOutID));
                concatEdgesScenarios(Utils.createSpace(4) + Edges.genEdgeStructureScenario(edgeParameterInToParameterOutID));

            }
        }


    }

    /**
     * Parse the "dependencies:" indentation level of the input and store them into repertories
     * @param dependencies to be parsed
     */
    public static void genDependencies(Object dependencies) {
        //Parse the input to gather information from the dependencies part of the input
        Dependencies d = new Dependencies(dependencies);

        if(d.getDependencies() != null) {
            for(Map.Entry<String, List<Map<String, String>>> tags : d.getDependencies().entrySet()){
                for(Map<String, String> attributes : tags.getValue()){

                    //Handle processes's properties
                    String property = attributes.get("localised_properties");
                    if(property != null){
                        String propertyID = Utils.genID();
                        String edgePropertyToTag = Utils.genID();
                        String edgePropertyToTagProcess = Utils.genID();

                        //Create the node corresponding to the current localised property
                        String node = Nodes.genNodesLocalisedProperty(property);
                        node = String.format(Nodes.genNodeStructureData(propertyID), node);
                        concatNodesData(node);

                        //Create the edge between the property and the corresponding tag
                        String edgeTag = String.format(Edges.genEdgeStructureData(edgePropertyToTagProcess), Edges.genEdgesInclude(repertoryTagsToIdTags.get(tags.getKey()), propertyID));
                        concatEdgesData(edgeTag);

                        //Create the edge between the property and the corresponding tags process
                        String edgeProcess = String.format(Edges.genEdgeStructureData(edgePropertyToTagProcess), Edges.genEdgesExchange(repertoryTagsToIdProcess.get(tags.getKey()), propertyID));
                        concatEdgesData(edgeProcess);

                        //Add the newly created nodes/edges to the scenario
                        concatNodesScenarios(Utils.createSpace(4) + Nodes.genNodeStructureScenarios(propertyID));
                        concatEdgesScenarios(Utils.createSpace(4) + Edges.genEdgeStructureScenario(edgePropertyToTag));
                        concatEdgesScenarios(Utils.createSpace(4) + Edges.genEdgeStructureScenario(edgePropertyToTagProcess));
                    }
                }
            }
        }

        handleCompositeObject();

    }

    /**
     * Handle the composite object : create a new node/process for the composite,
     * link it to its tag and then assign all the tags processes to the correct
     * object (the composite or the working)
     */
    private static void handleCompositeObject() {
        //Generating a new id for all composite Tags and create the linkage edge (ie from composite to process)
        for (String s : repertoryDependencies.values()) {
            //If there is a new dependency, add it to the map
            if (newCompositeObject.get(s) == null) {
                String id = Utils.genID();
                newCompositeObject.put(s, id);
            }

            //Generate the edge to the corresponding process defined in Tags
            String edgeCompositeObject = Utils.genID();
            String edge = String.format(Edges.genEdgeStructureData(edgeCompositeObject), Edges.genEdgesLink(newCompositeObject.get(s), repertoryTagsToIdTags.get(s)));
            concatEdgesData(edge);
            concatEdgesScenarios(Utils.createSpace(4) + Edges.genEdgeStructureScenario(edgeCompositeObject));
        }

        // Create all nodes for distributed actions
        for (Map.Entry<String, String> obj : newCompositeObject.entrySet()) {
            String distributedActionNodeID = obj.getValue();
            String distributedActionProcessID = Utils.genID();
            String edgeDistributedAction = Utils.genID();

            newCompositeObjectProcess.put(obj.getKey(), distributedActionProcessID);

            //Create the composite working object
            String nodeComposite = Nodes.genNodesWorkingObjectComposite(obj.getKey());
            nodeComposite = String.format(Nodes.genNodeStructureData(distributedActionNodeID), nodeComposite);
            concatNodesData(nodeComposite);

            //Create the corresponding process
            String processComposite = Nodes.genNodesDitributedAction(obj.getKey());
            processComposite = String.format(Nodes.genNodeStructureData(distributedActionProcessID), processComposite);
            concatNodesData(processComposite);

            //Create the edge between the process and the object
            String edge = String.format(Edges.genEdgeStructureData(edgeDistributedAction), Edges.genEdgesInclude(distributedActionNodeID, distributedActionProcessID));
            concatEdgesData(edge);


            concatNodesScenarios(Utils.createSpace(4) + Nodes.genNodeStructureScenarios(distributedActionNodeID));
            concatNodesScenarios(Utils.createSpace(4) + Nodes.genNodeStructureScenarios(distributedActionProcessID));
            concatEdgesScenarios(Utils.createSpace(4) + Edges.genEdgeStructureScenario(edgeDistributedAction));
        }



        //Linking all Tags to the corresponding object and process (composite or working)
        for (Map.Entry<String, String> e : repertoryTagsToIdTags.entrySet()) {
            String edgeToCorrespondingObject = Utils.genID();
            String edgeMainProcessToProcess = Utils.genID();

            String edgeToMain;
            String edgeMainProcess;

            //If the current tag depend on another one, link it to the composite, else to the main object
            if (repertoryDependencies.keySet().contains(e.getKey())) {
                edgeToMain = String.format(Edges.genEdgeStructureData(edgeToCorrespondingObject), Edges.genEdgesInclude(newCompositeObject.get(repertoryDependencies.get(e.getKey())), e.getValue()));
                edgeMainProcess = String.format(Edges.genEdgeStructureData(edgeMainProcessToProcess), Edges.genEdgesExchange(e.getValue(), newCompositeObjectProcess.get(repertoryDependencies.get(e.getKey()))));

            } else {
                edgeToMain = String.format(Edges.genEdgeStructureData(edgeToCorrespondingObject), Edges.genEdgesInclude(mainObjectID, e.getValue()));
                edgeMainProcess = String.format(Edges.genEdgeStructureData(edgeMainProcessToProcess), Edges.genEdgesExchange(e.getValue(), mainProcessID));

            }

            concatEdgesData(edgeToMain);
            concatEdgesData(edgeMainProcess);

            concatEdgesScenarios(Utils.createSpace(4) + Edges.genEdgeStructureScenario(edgeMainProcessToProcess));
            concatEdgesScenarios(Utils.createSpace(4) + Edges.genEdgeStructureScenario(edgeToCorrespondingObject));
        }
    }
}

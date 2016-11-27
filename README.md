# Frequent-Itemset-Mining

1. Pseudo Code
  -> Parse the input files.
  -> Store all the items in map<Integer, List<String>> database.
  -> Compute frequency of all the elements and store it is map<String, Integer> frequency. 
  -> Take minSup condition as input.
  -> If (frequency of item < minSup) {
  ->       Remove that item from dataSet. 
  -> }
  -> Sort the items in the dataSet based on the frequency as priority.
  -> Create a root and add child by accessing the all the lists from the map.
  -> After forming FP Tree create conditional FP tree and Pattern base considering minSup. 
  -> Output frequent patterns of FP Tree.
  
2. Creating FP Tree and Conditional FP Tree
    Step 1:
      i. Scan the data and find frequency of each item.
      ii. Discard infrequent items.
      iii. Sort frequent items in decreasing order based on their frequency.
      iv. Construct FP tree by adding the elements from all the lists.
      
    Step 2:
      i. FP growth algorithm extracts frequent item sets from the FP tree.
      ii. This is a bottom-up algorithm hence we move from leaves to roots.
      iii. First extract prefix path subtree ending in an item.
      iv. Each prefix path subtree is processed recursively to extract the frequent item sets. Finally,
          solutions are merged.
          
  Conditional FP Tree:
      i. This is the FP Tree that would be built if we only consider transactions containing a particular
         item set
      ii. Update the support counts along the prefix paths to reflect the number of tractions containing
         that particular item.
      iii. Remove the nodes containing information about the selected node.
      iv. Remove infrequent items from the prefix paths.

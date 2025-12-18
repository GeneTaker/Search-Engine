# Search Engine

This is my first attempt on a meaningful project, the aim of this project is 
to create a search engine, by following the software development lifecycle. 

## Requirements:
- Our search engine must be able to map searches to documents or sites that
contain the phrase
- Be able to discern sites from almost-correct searches
- The system should be able to accept queries from the user, and tokenise it
- Our system should present a list of pages that the user might be searching for, sorted by relevance
- Our system should be able to present/output the page to the user

## Architectural Decisions:
- The system will be designed as a monolith, as I will be the only person working on the project

## Architectural Characteristics:
- We seek to maximise:
* Performance: Users should be presented with a vast multitude of search suggestions with minimal wait times
* Elasticity + scalable(optional): Our system should be capable of adjusting loads at runtime

## Design:
Classes:
- **engine_controller**: Provides low level access to our search engine

- **response_formatter**: Formats results in a direct/informative manner to the user (could be an unnecessary abstraction of logic)

- **search_engine**: Orchestrates/directs actions within the system, pulls results from the index, sorts them according to some ranking, and formats it
    - If multiple ranking strategies are used later on, then we will require strategy pattern to be used to change between these during runtime 
    - Search engine will use composite pattern to handle logical operators in searches

- **index**: a map that maps tokens/keywords their respective postings

- **posting**: a wrapper of a document/page that contains metadata of a page. represents a single document occurrence of a term

- **search_result**: what the user sees after querying 

- **document_loader**: translates existing data into documents

- **document**: a wrapper for a file, to provide a safe, controlled means to access data

- **tokeniser**: small abstraction of tokenisation logic



## Notes:
- Search engine will be made so that logical operators can be applied to search queries to ensure tokens can be combined to give a relevant search result
    - These operators include AND, OR and NOT, and must be capitalised in the search query to be regarded as operators rather than tokens.

- At a later point, posting should be modified to account for searchFrequency so it can be used in ranking logic

## Design Update:
- To accomodate for logical operators during searches, composite pattern will be applied to accomodate for the logical operator evaluation
- Here, the logical operators represent the composite nodes whilst the leaf nodes can be represented by objects that are wrappers of query token -> List<Posting> mappings from the invertedIndex.
- When search is called in search engine, it will create a composite tree to evaluate the result of the query, and return a list of postings that satisfy it, which will later be mapped to SearchResult objects for the return result

To accomodate, the following classes will be created:
- **SearchNode**: An interface for the nodes in our composite pattern structure for searching
- **AndNode**: Implements *SearchNode*, represents an AND logical operator
- **OrNode**: Implements *SearchNode*, represents the OR logical operator
- **NotNode**: Implements *SearchNode*, represents the NOT logical operator (note the NOT operator will function as a complement operation rather than a set difference operation)
- **ImplicitAndNode**: Implements *SearchNode*, represents an implicit AND between adjacent tokens/words
- **TokenNode**: Implements *SearchNode*, the leaf node
- **NodeFactory**: A factory class that contains the abstracted creation logic of SearchNodes 

__Additional Note__: Each of these operations will have operator precedence, which we introduce when generating the tree, it will proceed in the order NOT -> AND -> OR. Additionally, we will have an "implicit and" when working with searches which has the highest operator precedence, where all adjacent tokens will have an implicit AND operation between them, thereby allowing us to perform logical operations on multi-word phrases rather than just singular words

## Design Update:
- To order searches by relevance, by default, the relevance of searches will be evaluated by the BM-25 search algorithm

This will involve the quantities:
__term frequency__, which involves:
- frequency that a query term appears in a document, posting term frequency

- number of documents containing a term, map size in search engine

- average document length in the total dataset, a method can be added in index for a total sum of doc lengths

- constant
- normalisation constant

__IDF__, which involves:
- total number of documents
- the number of documents containing a term
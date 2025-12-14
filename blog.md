This is my first attempt on a meaningful project, the aim of this project is 
to create a search engine, by following the software development lifecycle. 

## Requirements ##:
- Our search engine must be able to map searches to documents or sites that
contain the phrase
- Be able to discern sites from almost-correct searches
- The system should be able to accept queries from the user, and tokenise it
- Our system should present a list of pages that the user might be searching for, sorted by relevance
- Our system should be able to present/output the page to the user

## Architectural Decisions ##:
- The system will be designed as a monolith, as I will be the only person working on the project

## Architectural Characteristics ##:
- We seek to maximise:
* Performance: Users should be presented with a vast multitude of search suggestions with minimal wait times
* Elasticity + scalable(optional): Our system should be capable of adjusting loads at runtime

## Design ##:
Classes:
- engine_controller: Provides low level access to our search engine

- response_formatter: Formats results in a direct/informative manner to the user (could be an unnecessary abstraction of logic)

- search_engine: Orchestrates/directs actions within the system, pulls results from the index, sorts them according to some ranking, and formats it
    - If multiple ranking strategies are used later on, then we will require strategy pattern to be used to change between these during runtime 

- index: a map that maps tokens/keywords their respective postings

- posting: a wrapper of a document/page that contains metadata of a page. represents a single document occurrence of a term

- search_result: what the user sees after querying 

- document_loader: translates existing data into documents

- document: a wrapper for a file, to provide a safe, controlled means to access data

- tokeniser: small abstraction of tokenisation logic






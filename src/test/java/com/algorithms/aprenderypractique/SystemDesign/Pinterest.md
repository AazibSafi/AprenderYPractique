## Memcached: Backbone of Distributed Caching
Memcached and mcrouter form the backbone of Pinterest’s distributed caching infrastructure and play a critical role in Pinterest’s storage infrastructure stack. Memcached is an open source, highly efficient, in-memory key-value store written in pure C.

Memcached is an attractive choice as a caching solution:
- Thanks in part to its asynchronous event-driven architecture and multithreaded processing model, memcached is extremely efficient and easily amenable to horizontal scaling to meet capacity demands.
- Provides easy horizontal scalability to meet increased demand.

## Mcrouter: Backbone of Distributed Caching
Mcrouter is a layer 7 memcached protocol proxy that sits in front of the memcached fleet and provides powerful high availability and routing features.

Mcrouter applies a Consistent hashing algorithm against the cache key of every incoming request to deterministically shard the request to one host within a pool.

## Muse - Search Engine
We have an in-house search engine called Muse, which is a generic information retrieval platform.
Muse is heavily optimized for online serving and provides a rich set of search and aggregation queries.
Muse is used at Pinterest for different critical use cases like home feed, ads, shopping, etc.
Ixia uses Muse as its search engine to provide rich search functionalities in the non-row-key columns.
> https://medium.com/pinterest-engineering/building-scalable-near-real-time-indexing-on-hbase-7b5eeb411888


## Xenon - Flink
Xenon is the Flink-based stream processing platform at Pinterest. The mission is to build a reliable, scalable, easy-to-use, and efficient stream platform to enable data-driven products and timely decision making.
> https://medium.com/pinterest-engineering/unified-flink-source-at-pinterest-streaming-data-processing-c9d4e89f2ed6

## Efficient Ad Retrieval - Offline ANN
ANN - Approximate Nearest Neighbors
Capture user's offsite engaged items
Show those ads to users on pinterest
Called Dynamic retarget Ads
- Less infra cost in offline ANN
- Better Engagement and Conversion
> https://medium.com/pinterest-engineering/unlocking-efficient-ad-retrieval-offline-approximate-nearest-neighbors-in-pinterest-ads-6fccc131ac14

## Ads Serving Systems
- Ad Candidate
- Ad Retrieving

- Delivers 3B Ads impression per day
- Generates $2.8 Billion in Ads revenue

> FB deliver 6 billion ad impressions per day

> https://medium.com/pinterest-engineering/redesigning-pinterests-ad-serving-systems-with-zero-downtime-3253d2432a0c

## Aperture
**General Definition:** Aperture is the ideal time and place at which the customer is most receptive to receiving and paying attention to a brand message.<br>
**Pinterest:** in-house data storage and online event tracking service that achieves the ads user action counting requirements.
> https://medium.com/pinterest-engineering/building-a-real-time-user-action-counting-system-for-ads-88a60d9c9a

## Why Pinterest
> https://medium.com/pinterest-engineering/create-the-engineering-career-you-love-at-pinterest-c1010e69f31a
> https://newsroom.pinterest.com/en-ca/?filters=ads


## Terms
- Monetization stakeholders
- Bidding/Pacing
- SSP (modularize) - > loose coupling
- Cost-per-click (CPC)
- CTR - Click Through Rate
- PinnerSage -> user's profile of last engagement for Ad recommendations
- Pinterest Data Tech Stack: https://www.junaideffendi.com/p/pinterest-data-tech-stack

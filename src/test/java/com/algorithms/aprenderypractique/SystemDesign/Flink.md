# Flink
Streaming process engines
https://www.hellointerview.com/learn/system-design/deep-dives/flink

## Concepts
> source -> stream -> operators -> sink

- Flink is STATEFUL
- Restores from CHECKPOINT
- Make it FAULT TOLERANT
- WATERMARKS -> in order events by timestamp
- Windows -> group elements by time and count


## Advantages
- Stateful
- dedupe
- joins
- out-of-order handling
- async lookups
- end-to-end exactly once


> For simpler logics and stateless behavior, Kafka Confluent is better.
> 
> Small stateless consumers are cheaper to run/operate.
> 
> Flink is expense.

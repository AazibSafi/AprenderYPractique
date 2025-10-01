Design a Rate Limiter

https://www.hellointerview.com/learn/system-design/problem-breakdowns/distributed-rate-limiter

Functional Requirements
- System should identify user by userId, IP or API key
- System should limit the user for HTTP request for the Rule of 100 APis per minute per user
- System should reject the Api call if rule violates and send 429 code

Non-Functional Requirements
- Should be scalable
- availability > consistency
- minimal Latency <10ms

System Interface
isRequestAllowed(clientId, ruleId)


Client -> Api Gateway (LB) -> Backend Service
                |
           Api Limiter


Fixed Window Counter
Sliding Window Counter
Token Bucket

Refill bucket at 1 token per minute rate

Store token in the Redis

Race Condition: Two Api Gateways can read and write to Redis

Use Lua Scripting: Atomic operation to read-calculate-update tokens


Scalable:
Shard Redis
Bucketing of Redis based on user_id


Fail Closed
Fail Open

Replicas of each redis shard

Dynamic config: Zookeeper
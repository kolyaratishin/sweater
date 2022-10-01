create table user_subscriptions (
        channel_id bigint not null REFERENCES usr,
        subscriber_id bigint not null REFERENCES usr,
        PRIMARY KEY (channel_id, subscriber_id)
);
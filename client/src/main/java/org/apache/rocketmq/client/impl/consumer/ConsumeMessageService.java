/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.rocketmq.client.impl.consumer;

import java.util.List;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.common.protocol.body.ConsumeMessageDirectlyResult;

public interface ConsumeMessageService {
    //
    void start();

    void shutdown();

    //更新消费线程池的核心线程数。
    void updateCorePoolSize(int corePoolSize);

    //增加一个消费线程池的核心线程数。
    void incCorePoolSize();

    //减少一个消费线程池的核心线程数。
    void decCorePoolSize();

    //获取消费线程池的核心线程数。
    int getCorePoolSize();

    //如果一个消息已经被消费过了，但是还想再消费一次，就需要实现这个方法。
    ConsumeMessageDirectlyResult consumeMessageDirectly(final MessageExt msg, final String brokerName);

    //将消息封装成线程池任务，提交给消费服务，消费服务再将消息传递给业务消费进行处理。
    void submitConsumeRequest(
        final List<MessageExt> msgs,
        final ProcessQueue processQueue,
        final MessageQueue messageQueue,
        final boolean dispathToConsume);
}

/*
 * Copyright 2012-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ckgj;

import com.ckgj.models.Message;
import com.ckgj.services.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Dave Syer
 */

@Service
public class InMemoryMessageRepository {
	@Autowired
	private final MessageRepository messageRepository;

	@Autowired
	public InMemoryMessageRepository(MessageRepository userRepository) {

		this.messageRepository = userRepository;
	}

	public Message findMessage(Long id) {
		return this.messageRepository.getOne(id);
	}

	public void deleteMessage(Long id) {
		this.messageRepository.delete(id);
	}

	public Iterable<Message> findAll() {
		return this.messageRepository.findAll();
	}

	public Message save(Message message) {
		return this.messageRepository.saveAndFlush(message);
	}
}

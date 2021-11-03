/**
 * Copyright (C) 2021 nierax
 * This program is free software; you can redistribute it and/or modify it under the terms of 
 * the GNU General Public License as published by the Free Software Foundation; either version 3 
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; 
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 * 
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program; 
 * if not, see <http://www.gnu.org/licenses/>. 
 */
package de.lexasoft.common.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * @author nierax
 *
 */
class ResultTest {

	private final static Stream<Arguments> is_erroneous() {
		return Stream.of(//
		    Arguments.of(//
		        MessageList.of()//
		            .addMessage(Message.of(MessageId.of(), MessageText.of("test-01-01"), MessageSeverity.ERROR)),
		        true),
		    Arguments.of( //
		        MessageList.of()//
		            .addMessage(Message.of(MessageId.of(), MessageText.of("test-01-01"), MessageSeverity.ERROR))
		            .addMessage(Message.of(MessageId.of(), MessageText.of("test-01-02"), MessageSeverity.ERROR)),
		        true),
		    Arguments.of( //
		        MessageList.of()//
		            .addMessage(Message.of(MessageId.of(), MessageText.of("test-01-01"), MessageSeverity.WARNING))
		            .addMessage(Message.of(MessageId.of(), MessageText.of("test-01-02"), MessageSeverity.INFO)),
		        false),
		    Arguments.of( //
		        MessageList.of()//
		            .addMessage(Message.of(MessageId.of(), MessageText.of("test-01-01"), MessageSeverity.WARNING))
		            .addMessage(Message.of(MessageId.of(), MessageText.of("test-01-02"), MessageSeverity.INFO))
		            .addMessage(Message.of(MessageId.of(), MessageText.of("test-01-03"), MessageSeverity.ERROR)),
		        true),
		    Arguments.of(//
		        null, //
		        false));
	}

	/**
	 * Test method for {@link de.lexasoft.common.model.Result#isErroneous()}.
	 */
	@ParameterizedTest
	@MethodSource
	final void is_erroneous(MessageList messages, boolean expected) {
		// Prepare
		Result<String> cut = ResultBuilder.of("is_erroneous_1_error")//
		    .withMessages(messages)//
		    .build();
		// Run and Check
		assertEquals(expected, cut.isErroneous());
	}

}

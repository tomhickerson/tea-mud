/*
 *  Copyright 2016. Ivan Stuart
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.ivstuart.tmud.client;

import com.ivstuart.tmud.server.LaunchMud;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Recommend putty or tintin++ however please please double check site and
 * source of any download to ensure that there any binary files are trustworthy.
 * In the meantime this can be used as a safe local client, which does not
 * handle the ansi colour coding.
 * 
 * To switch this off from command line use "configdata ansi" to toggle on and
 * off.
 * 
 * @author Ivan
 * 
 */
public class LaunchClient implements Runnable {

	private static final Logger LOGGER = LogManager.getLogger();

	private boolean isRunning = true;
	private BufferedReader bufferReader = null;
	private PrintWriter pw = null;
	private Socket socket = null;

	public LaunchClient() throws IOException {
		super();
		String ip = InetAddress.getLocalHost().getHostAddress(); // Same as you
																	// have
																	// configured
																	// your mud
																	// server
		int port = LaunchMud.getMudServerPort();

        LOGGER.info("Opening socket connection on " + ip + ":" + port);

		try {
			socket = new Socket(ip, port);
		} catch (Exception e) {
			LOGGER.error("Problem opening socket connection on " + ip + ":"
					+ port, e);
		}

		bufferReader = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));

		pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),
				true);
	}

    public static void main(String argv[]) throws IOException {

        LaunchClient client = init();
        client.readWriteStreams();

    }

    public static LaunchClient init() throws IOException {
        LaunchClient client = new LaunchClient();

        Thread responseThread = new Thread(client);
        responseThread.start();

        return client;
    }

	public void readWriteStreams() throws IOException {

		BufferedReader stdIn = new BufferedReader(new InputStreamReader(
				System.in));

		String userInput;

		while ((userInput = stdIn.readLine()) != null && isRunning && !pw.checkError()) {
			pw.println(userInput); // pushing input over to socket.
			System.out.println();
		}

	}

	/**
	 * Used by integration tests only
	 * 
	 * @param userInput
	 */
	public void send(String userInput) {
		LOGGER.info("Sending " + userInput);
		if (isRunning) {
			pw.println(userInput); // pushing input over to socket.
			System.out.println();
		}
	}

	@Override
	public void run() {
		while (isRunning) {
			try {
				if (bufferReader.ready()) {
					System.out.println(bufferReader.readLine());
				}
			} catch (IOException e) {
                LOGGER.error(e);
                isRunning = false;
			}
		}
	}

	public void close() {
		isRunning = false;
		if (socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
                LOGGER.error(e);
            }
		}
		if (bufferReader != null) {
			try {
				bufferReader.close();
			} catch (IOException e) {
                LOGGER.error(e);
            }
		}
		if (pw != null) {
			pw.close();
		}
	}
}

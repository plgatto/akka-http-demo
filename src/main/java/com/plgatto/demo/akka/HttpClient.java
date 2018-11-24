package com.plgatto.demo.akka;

import java.util.concurrent.CompletionStage;

import akka.actor.ActorSystem;
import akka.http.javadsl.Http;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.stream.ActorMaterializer;
import akka.stream.Materializer;

public class HttpClient {
	public static void main(String[] args) {
		final ActorSystem system = ActorSystem.create();
		final Materializer materializer = ActorMaterializer.create(system);
		@SuppressWarnings("unused")
		final CompletionStage<HttpResponse> responseFuture = Http.get(system)
				.singleRequest(HttpRequest.create("http://localhost:8080/hello"), materializer)
				.whenCompleteAsync((response,ex) -> { System.out.println(response.toString());});
	}
}
<%@ include file="/init.jsp"%>

<section class="conteudo-associado-model moelecule--featured tempo-portlet">
	<header>
		<div class="atom--rect sm-mg-right"></div>
		<h5 class="sm-mg-left">Tempo</h5>
	</header>

	<article class="bg-blue md-pd">
		<c:choose>
			<c:when test="${not empty weather}">
				<c:forEach items="${weather.forecasts}" var="forecast" varStatus="loop">
					<c:choose>
						<c:when test="${loop.index == 0}">
							<div class="centered" id="top-summers">
								<div>
									<p class="white rm-mg">Máxima ${forecast.maxima}</p>
									<p class="white rm-mg">Mínima ${forecast.minima}</p>
								</div>
								<div class="md-mg-left figure-summer">
									<img src="${forecast.URLIcon}" alt=""/>
								</div>
							</div>
						</c:when>
						<c:otherwise>
							<div class="centered bottom-summers sm-mg-top">
								<p class="white rm-mg">
									<span>${forecast.dayOfWeek}</span>
									<span>${forecast.maxima}</span>
									<span>${forecast.minima}</span>
								</p>
								<img src="${forecast.URLSmallIcon}" alt=""/>
							</div>
						</c:otherwise>
					</c:choose>
				
				</c:forEach>
				<p class="white rm-mg">
					<span>${weather.nome} / ${weather.uf}</span>
				</p>
			</c:when>
			<c:otherwise>
				<p class="white rm-mg">
					<span>Previsão indisponível</span>
				</p>
			</c:otherwise>
		</c:choose>		
	</article>
</section>
	<div id="header" class="">
		<div class="gauche">
			<ul class="menu">
				<c:if test="${not empty user}">
					<li class="nomUser">
						<img alt="user" src="resources/images/user.png">
							<span>${user.nom}</span>
					</li> 
					<li class="options">
						<ul>
							<li>
								<a href="mesImages">Mes examens</a>
							</li>
							<li>
								<a href="mesUsages">Mes usages</a>
							</li>
						</ul>
					</li>
					<li class="import">
						<a href="import">
							<img alt="importer" src="resources/images/importer.png">
							<span>Importer des images</span>
						</a>
					</li>
				</c:if>
				<li class="import">
					<a href=".">
						<img alt="rechercher" src="resources/images/loupe.png">
						<span>Effectuer une recherche</span>
					</a>
				</li>
			</ul> 
		</div>
		<div class="droite">
			<ul class="menu">
				<li class="etatConnexion">
					<c:choose>
						<c:when test="${empty user}">
							<span class="connexion">
								<a href="identification">
									<img alt="connexion" src="resources/images/connexion.png">
									Connexion
								</a>
							</span>
						</c:when>
						<c:otherwise>
							<span class="deconnexion">
								<a href="deconnexion">
									<img alt="deconnexion" src="resources/images/deconnexion.png">
									D�connexion
								</a>
							</span>
						</c:otherwise>
					</c:choose>
				</li>  
			</ul>
		</div>
	</div>
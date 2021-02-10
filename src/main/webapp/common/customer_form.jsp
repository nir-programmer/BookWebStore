<table class="form">
				<tr>
					<td align="right">E-mail Address:</td>
					<td align="left"><input type="text" id="email" name="email"
						value="${customer.email}" size="45"></td>
				</tr>

				<%--Replaced For PayPal!(first name , last name)
				 <tr>
					<td align="right">Full Name:</td>
					<td align="left"><input type="text" name="fullName"
						id="fullName" size="45" value="${customer.fullname}"></td>
				</tr> --%>

				<tr>
					<td align="right">First Name:</td>
					<td align="left"><input type="text" name="firstname"
						id="firstname" size="45" value="${customer.firstname}"></td>
				</tr>

				<tr>
					<td align="right">Last Name:</td>
					<td align="left"><input type="text" name="lastname"
						id="lastname" size="45" value="${customer.lastname}"></td>
				</tr>


				<tr>
					<td align="right">Password:</td>
					<td align="left"><input type="password" id="password"
						name="password" size="45" value="${customer.password}"></td>
				</tr>

				<tr>
					<td align="right">Confirm Password:</td>
					<td align="left"><input type="password" id="confirmPassword"
						name="confirmPassword" value="${customer.password}" size="45"></td>
				</tr>

				<tr>
					<td align="right">Phone Number:</td>
					<td align="left"><input type="text" id="phone" name="phone"
						size="45" value="${customer.phone}"></td>
				</tr>
				<!-- For PayPal.. -->
				<%-- <tr>
					<td align="right">Address:</td>
					<td align="left"><input type="text" id="address"
						name="address" value="${customer.address}" size="45"></td>
				</tr> --%>
				<tr>
					<td align="right">Address Line 1:</td>
					<td align="left">
					<input type="text" id="address1"
						name="address1" value="${customer.addressLine1}" size="45">
					</td>
				</tr>
				
				<tr>
					<td align="right">Address Line 2:</td>
					<td align="left">
					<input type="text" id="address2"
						name="address2" value="${customer.addressLine2}" size="45">
					</td>
				</tr>

				<tr>
					<td align="right">City:</td>
					<td align="left"><input id="city" name="city" type="text"
						value="${customer.city}" size="45"></td>
				</tr>
				<!-- For PayPal.. -->
				<tr>
					<td align="right">State:</td>
					<td align="left">
						<input id="state" name="state" type="text" value="${customer.state}" size="45">
					</td>
				</tr>
				<tr>
					<td align="right">Zip Code:</td>
					<td align="left"><input id="zipCode" name="zipCode"
						type="text" value="${customer.zipcode}" size="45"></td>
				</tr>

				<%-- FOR PAYPAL
				<tr>
					<td align="right">Country:</td>
					<td align="left"><input type="text" id="country"
						name="country" value="${customer.country}" size="45"></td>
				</tr> --%>
				
				<tr>
					<td align="right">Country:</td>
					<td align="left">
						<select  id="country" name="country" >
						<c:forEach items="${mapCountries}" var="country">
						<!-- country.key is the country name.country.value is the country code -->
						<option value="${country.value}"
							 <c:if test = '${customer.country eq country.value}'>selected='selected'</c:if> >${country.key}
						</option>
						
						</c:forEach>
						</select>
					</td>
				</tr>

				<tr>


					<td colspan="2" align="center">
						<button type="submit" value=Save">Save</button>&nbsp;&nbsp;&nbsp;
						<button onclick="javascript:history.go(-1);" value="Cancel">Cancel</button>
						<!--IMPORTANT NOTE : HE USED The following code for the Cancel button!: -->
						<!-- input type="button" value="Cancel" onclick="history.go(-1);" /> -->
						<!-- <button id="cancelButton" value="Cancel">Cancel</button> -->
					</td>

				</tr>
			</table>
import play.api.libs.json._
val jsonString = "[{\"id\":\"187325cf-b554-49e8-b048-34f33b242483\",\"title\":\"832\",\"content\":\"cccccc832\",\"email\":\"vietngc@gmail.com\",\"createdDate\":\"2016-03-03 11:29:23\"},{\"id\":\"1a2a6d74-2bae-4452-bd9d-8a9952e8f32b\",\"title\":\"New article\",\"content\":\"Test for new ar\",\"email\":\"diu@gmail.com\",\"createdDate\":\"2016-03-03 13:28:19\"},{\"id\":\"20b9b3a2-8c6e-41fb-b2d8-16f504a6c352\",\"title\":\"83\",\"content\":\"aaaaaaxxx83\",\"email\":\"vietngc@gmail.com\",\"createdDate\":\"2016-03-03 11:18:3\"},{\"id\":\"3358d3de-69de-4933-993e-2accd66a891f\",\"title\":\"Viet\",\"content\":\"Content of article\",\"email\":\"vietngc@gmail.com\",\"createdDate\":\"2016-03-03 13:27:13\"},{\"id\":\"386787ec-d3d8-492c-bc64-b3e9083a4f1b\",\"title\":\"833\",\"content\":\"833\",\"email\":\"vietngc@gmail.com\",\"createdDate\":\"2016-03-03 11:38:18\"},{\"id\":\"3bda43e3-57d0-405c-a05a-85ab6ad60cda\",\"title\":\"93\",\"content\":\"893\",\"email\":\"vietngc@gmail.com\",\"createdDate\":\"2016-03-03 11:18:58\"},{\"id\":\"3cb47c40-d565-48ac-afbd-e87e3f4038ec\",\"title\":\"title\",\"content\":\"content\",\"email\":\"author\",\"createdDate\":\"2016-03-03 10:1:12\"},{\"id\":\"3e17d576-332f-485d-9cec-bf26faf06af2\",\"title\":\"bbb\",\"content\":\"aaaaaaxxx\",\"email\":\"vietngc@gmail.com\",\"createdDate\":\"2016-03-03 11:18:3\"},{\"id\":\"498eb220-c7ac-4b63-87ed-822de38c1f82\",\"title\":\"title\",\"content\":\"content\",\"email\":\"author\",\"createdDate\":\"2016-03-03 10:24:8\"},{\"id\":\"71033e60-1e09-4bbb-9c79-350fb118da38\",\"title\":\"931\",\"content\":\"8931\",\"email\":\"vietngc@gmail.com\",\"createdDate\":\"2016-03-03 11:19:57\"},{\"id\":\"896f3948-027f-4d3f-a615-8643ee6d5c1a\",\"title\":\"title\",\"content\":\"content\",\"email\":\"author\",\"createdDate\":\"2016-03-03 10:9:3\"},{\"id\":\"8a48f360-2012-415f-9265-af437b9e4c8e\",\"title\":\"title\",\"content\":\"content\",\"email\":\"author\",\"createdDate\":\"2016-03-03 9:59:26\"},{\"id\":\"912950c9-5f51-436b-841a-b69350d7f68f\",\"title\":\"title\",\"content\":\"content\",\"email\":\"author\",\"createdDate\":\"2016-03-02 19:54:25\"},{\"id\":\"a3ae8928-3ad9-4f3b-af50-df23f454e652\",\"title\":\"931\",\"content\":\"8931\",\"email\":\"vietngc@gmail.com\",\"createdDate\":\"2016-03-03 11:19:36\"},{\"id\":\"a7b6926d-a1f1-449e-ab22-e07509eb6389\",\"title\":\"Bizman\",\"content\":\"Good job!\",\"email\":\"vietngc@gmail.com\",\"createdDate\":\"2016-03-03 11:14:14\"},{\"id\":\"abc\",\"title\":\"12314\",\"content\":\"asfasf\",\"email\":\"a@gmail.com\",\"createdDate\":\"2016-02-25 18:0:0\"},{\"id\":\"b7d8d32e-32ef-457a-9027-4893b86b6fac\",\"title\":\"title\",\"content\":\"content\",\"email\":\"author\",\"createdDate\":\"2016-03-03 10:41:37\"},{\"id\":\"cb1d42e4-7e73-4fa5-941b-78cb8e1060c2\",\"title\":\"eeeecc\",\"content\":\"yyyyzzzzzz\",\"email\":\"vietngc@gmail.com\",\"createdDate\":\"2016-03-03 11:11:32\"},{\"id\":\"cd8bf95c-0983-4331-a54f-0d1707423538\",\"title\":\"ccccc\",\"content\":\"cccccc\",\"email\":\"vietngc@gmail.com\",\"createdDate\":\"2016-03-03 11:29:2\"},{\"id\":\"ceb6592f-6ac5-4776-84a2-62624e520325\",\"title\":\"83\",\"content\":\"aaaaaaxxx83\",\"email\":\"vietngc@gmail.com\",\"createdDate\":\"2016-03-03 11:18:9\"},{\"id\":\"d170b908-9bae-4465-a426-f9c5270caba5\",\"title\":\"title\",\"content\":\"content\",\"email\":\"author\",\"createdDate\":\"2016-03-03 9:59:13\"}]"
val json = Json.parse(jsonString)
println( (json \\ "id" ).length )


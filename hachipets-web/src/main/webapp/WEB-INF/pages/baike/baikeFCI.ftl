<html>
<head>
    <title>国际犬种FCI标准-宠物百科</title>
    <link href="/css/baike-fci.css" rel="stylesheet">
</head>
<body>
<div class="container">
<div class="baike-container">
<div class="baike-nav">
    <ul class="breadcrumb">
        <li><a href="/baike">宠物百科</a><span class="divider">&gt;</span> </li>
        <li class="active">国际犬种FCI标准</li>
    </ul>
</div>
<div class="baike-fci">
    <h4 class="title">一、什么是FCI?</h4>
    <div class="content">
        <p>
            FCI(The Federation Cynologique Internationale)--世界畜犬联盟也叫世界犬协成立于1911年，最初由比、法、德、奥、荷五国联合创立。目前是世界上最大的犬组织。
        </p>
        <p>
            FCI的主要职责是：监察其会员机构每年举办4次以上的全犬种犬展；统一各个犬种原产国的标准，并广泛公布；制定国际犬展规则；组织、评审以及颁发冠军登录头衔；制定协会成员国血统记录，认定犬种标准。
        </p>
        <p>
            虽然是一个统一的国际性组织，但是FCI有比较强的兼容性，它包含有80多个成员机构，日本的JKC、法国的SCC、还有中国台湾的KCC等机构都是其成员机构。这些机构都保留有自己的特性，但都归属于FCI统一管理，并且使用共同的积分制度。。
        </p>
        <p>
            目前 FCI承认世界上337品种的犬类并将其所有认可的纯种犬分为10个组别，其中每个组别又按产地和用途划分出不同的类别。
        </p>
        <p>
            作为犬界领军组织，FCI致力于发展繁殖优良的正统犬，并完善出一套规范合理的纯种犬管理繁殖理念，将正统犬的管理和繁殖更加系统化、优越化。
        </p>
    </div>

     <h4 class="title">二、FCI的意义：</h4>
     <div class="content">
         <p>
              血统证书可以说是猫和狗的户口本，它是该犬及祖宗三代的健康状况、训练成绩等记录。我们可以依据血统书的记载以其祖先之优劣来判定该犬的血统及在繁殖上做改良的依据。而犬只的改良依血统的配合及近亲繁殖而定型的。作为拥有血统证书的纯种猫（狗）或其子女，其身价要高于其他没有血统证书的宠物，由于价值问题宠物店或繁殖场都会很精心地照料它们，购买这样的宠物相对要放心一些。目前国内尚无此方面的健全机构，真正建立血统证书档案的很少，但从国外进口的猫（狗）多数应具有血统证书。
         </p>
        <p>
            意义：犬的血统证书是由正规合法的犬业俱乐部、协会颁发给繁育者，用以确认其繁育的某一只犬的真实合法身份。世界各地的血统证书不尽相同，但大体会包含以下内容：犬的姓名、品种、性别、出生日，皮毛颜色及其他特征，繁育者和繁育犬舍，该犬的四代直系血亲的详细资料，登录号码、刺青号码、dna号码、髋关节号码和植入晶片的记录，比赛记录和转让记录，sv的血统证书还要有训练程度的记录。犬的血统证书就象人的身份证，它是判定某一只犬血统、身份的重要依据。在犬业发展中，血统证书具有相当重大的意义。
        </p>
        <p>
            <ul>
                <li>
                    首先，血统证书有利于俱乐部、协会的规范管理。每一只得到血统证书的犬，同时在俱乐部、协会也有了备案登记，因此俱乐部、协会能随时掌握它所管辖范围内犬和犬舍的状况。只有获得血统证书的犬，才能有资格参加犬展和进行繁殖，这体现了俱乐部、协会的科学性和权威性。
                </li>
                <li>
                    其次，血统证书是繁育的重要根据。血统证书的很多内容，特别是四代直系血亲的记载，为繁育者利用这只犬进行繁育提供了非常有用的信息。
                </li>
                <li>
                    第三，血统证书有助犬的医疗保健。dna号码和髋关节号码可以帮助犬主、医生了解犬的先天生理状况，更容易采取针对性的医疗手段和保健措施。
                </li>
                <li>
                    最后，血统证书是犬只销售转让的必备手续。销售转让犬只，犬主必须向客户出示血统证书，以保证让客户全面了解该犬的情况和真实身份，这是业界通行的规则。而且，血统证书要随犬一起交由新主人保管。在正规发达的犬业市场，血统证书系统起着非常重要的作用，绝对是不可或缺的。
                </li>
            </ul>
        </p>
    </div>

    <h4 class="title">三、FCI的10组犬只介绍：</h4>
    <div class="content">
        <#list groupList as group>
        <div class="baike-fci-row" id="G${group.groupId}" name="G${group.groupId}">
            <div class="baike-fci-title">${(group.groupName)!""}</div>
            <div class="baike-fci-title-en">${(group.groupEnName)!""}</div>
            <div class="baike-fci-body">
                <#if sectionMap[group.groupId+""]??>
                <ul>
                <#list sectionMap[group.groupId+""] as section>
                    <li>
                        <a href="/baike/FCI/section/${(section.sectionId)!0}">${(section.sectionName)!""}</a><span class="en">${(section.sectionEnName)!""}</span>
                    </li>
                </#list>
                </ul>
                </#if>
            </div>
        </div>
        </#list>
    </div>
</div>
</div>
</div>
</body>
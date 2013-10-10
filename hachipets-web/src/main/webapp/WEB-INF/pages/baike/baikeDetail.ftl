<html>
<head>
    <title>${(type.name)!""}-宠物百科</title>
    <link href="/css/baike-detail.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="baike-container">
        <div class="baike-nav">
            <ul class="breadcrumb">
                <li><a href="/baike">宠物百科</a><span class="divider">&gt;</span> </li>
                <#list categoryPath as category>
                    <#if !category_has_next>
                        <#assign currentCategory = category>
                    </#if>
                    <li><a href="#">${(category.name)!""}</a><span class="divider">&gt;</span></li>
                </#list>
                <li class="active">${(type.name)!""}</li>
            </ul>
        </div>

        <#if (type.picUrl)??>
        <div class="baike-pic">
            <div><img src="${type.picUrl}" ></div>
        </div>
        </#if>

        <div class="baike-desc">
            <div class="baike-title"><h4>${(type.name)!""}</h4></div>
            <div class="baike-story">
                <div class="baike-story-container">
                    ${type.desc}
                </div>
            </div>
        </div>

        <div class="baike-detail">
            <div class="baike-detail-row baike-basic">
                <div class="baike-detail-title">基本信息</div>
                <div class="baike-detail-body">
                    <#list attrList as attr>
                    <dl>
                        <dt>${attr.name}:</dt>
                        <dd>${attr.value}</dd>
                    </dl>
                    <#if attr.singleLine>
                    <div class="clear"></div>
                    </#if>
                    </#list>
                    <dl>
                        <dt>功&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;能:</dt>
                        <dd>伴侣犬 / 狩望猎犬 / 工作犬</dd>
                    </dl>
                    <dl>
                        <dt>毛&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;长:</dt>
                        <dd>长毛</dd>
                    </dl>
                    <div class="clear"></div>
                    <dl>
                        <dt>身&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;高:</dt>
                        <dd>雄性: 64-74cm 雌性: 61-73cm</dd>
                    </dl>
                    <dl>
                        <dt>寿&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;命:</dt>
                        <dd>12-15年</dd>
                    </dl>
                    <div class="clear"></div>
                    <dl>
                        <dt>体&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;重:</dt>
                        <dd>雄性: 23-27kg 雌性: 20-25kg</dd>
                    </dl>
                    <div class="clear"></div>
                    <dl>
                        <dt>FCI&nbsp;&nbsp;组别:</dt>
                        <dd><a href="/baike/FCI#G1">第10组 视觉型猎犬</a></dd>
                    </dl>
                    <dl>
                        <dt>FCI&nbsp;&nbsp;类别:</dt>
                        <dd><a href="/baike/FCI/section/1">第1类 长毛或流苏状视觉型猎犬</a></dd>
                    </dl>
                    <div class="clear"></div>
                    <dl>
                        <dt>CFA&nbsp;&nbsp;链接:</dt>
                        <dd><a href="http://www.cfainc.org/Breeds/BreedsAB/Abyssinian.aspx">CFA Abyssinian</a><span class="memo"><a href="/baike/CFA">什么是CFA?</a></span></dd>
                    </dl>
                    <div class="clear"></div>
                    <dl>
                        <dt>毛&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;色:</dt>
                        <dd class="wide">黑罩红、黑色、铁包金、乳色（金色）、白色、斑色、蓝色、多米诺色</dd>
                    </dl>
                    <div class="clear"></div>
                </div>
            </div>
            <div class="baike-detail-row baike-feature">
                <div class="baike-detail-title">特征标准</div>
                <div class="baike-detail-body">
                    <dl>
                        <dt>粘&nbsp;人&nbsp;程&nbsp;度:</dt>
                        <dd><span class="rank rank-star10" ></span></dd>
                    </dl>
                    <dl>
                        <dt>初养适应度:</dt>
                        <dd><span class="rank rank-star10" ></span></dd>
                    </dl>
                    <div class="clear"></div>
                    <dl>
                        <dt>犬&nbsp;叫&nbsp;程&nbsp;度:</dt>
                        <dd><span class="rank rank-star10" ></span></dd>
                    </dl>
                    <dl>
                        <dt>掉&nbsp;毛&nbsp;程&nbsp;度:</dt>
                        <dd><span class="rank rank-star10" ></span></dd>
                    </dl>
                    <div class="clear"></div>
                    <dl>
                        <dt>体&nbsp;味&nbsp;程&nbsp;度:</dt>
                        <dd><span class="rank rank-star10" ></span></dd>
                    </dl>
                    <dl>
                        <dt>美&nbsp;容&nbsp;程&nbsp;度:</dt>
                        <dd><span class="rank rank-star40" ></span></dd>
                    </dl>
                    <div class="clear"></div>
                    <dl>
                        <dt>对孩子友善:</dt>
                        <dd><span class="rank rank-star10" ></span></dd>
                    </dl>
                    <dl>
                        <dt>对生人友善:</dt>
                        <dd><span class="rank rank-star10" ></span></dd>
                    </dl>
                    <div class="clear"></div>
                    <dl>
                        <dt>对动物友善:</dt>
                        <dd><span class="rank rank-star30" ></span></dd>
                    </dl>
                    <dl>
                        <dt>运&nbsp;&nbsp;&nbsp;动&nbsp;&nbsp;&nbsp;量:</dt>
                        <dd><span class="rank rank-star40" ></span></dd>
                    </dl>
                    <div class="clear"></div>
                    <dl>
                        <dt>可&nbsp;训&nbsp;练&nbsp;性:</dt>
                        <dd><span class="rank rank-star20" ></span></dd>
                    </dl>
                    <dl>
                        <dt>口&nbsp;水&nbsp;程&nbsp;度:</dt>
                        <dd><span class="rank rank-star10" ></span></dd>
                    </dl>
                    <div class="clear"></div>
                    <dl>
                        <dt>耐&nbsp;寒&nbsp;程&nbsp;度:</dt>
                        <dd><span class="rank rank-star40" ></span></dd>
                    </dl>
                    <dl>
                        <dt>耐&nbsp;热&nbsp;程&nbsp;度:</dt>
                        <dd><span class="rank rank-star40" ></span></dd>
                    </dl>
                    <div class="clear"></div>
                    <dl>
                        <dt>城市适应度:</dt>
                        <dd><span class="rank rank-star40" ></span></dd>
                    </dl>
                    <div class="clear"></div>
                </div>
            </div>
            <div class="baike-detail-row baike-characteristics">
                <div class="baike-detail-title">其他信息</div>
                <div class="baike-detail-body">
                    <span class="feature">
                        [头部]
                    </span>
                    <span class="info">
                        头部长度恰当，显得非常精致，脑袋和前脸显得均匀和谐。轻微突起的鼻梁骨，形成了罗马面貌，其中心线沿着前脸上升到轻微（或没有）的止部，消失在眼睛前面，所以视线清晰，没有任何冲突，下颌显得非常有力，颌部长而强烈；嘴巴为钳状咬和，上下颚牙齿均匀匹配，完全对齐，无上颚突出或下颚突出。对这一品种，这样的嘴是很难得的。剪状咬和更强烈，且比钳状咬和更容易繁殖，剪状咬和（下颚的牙齿紧贴上颚的牙齿内侧）不属于缺陷。后枕骨非常突出，头顶的"头发"是丝状的、长长的毛发。耳朵：长，位置大约与外眼角在同一水平线，耳廓的长度可以延伸到鼻尖，被长而丝状的毛发所覆盖。眼睛：杏仁状（差不多是三角的），不能太突出，颜色深。鼻镜：大小合适，黑色。缺陷：粗糙，象被截断，上颚突出或下颚突出，眼睛圆、突出或颜色浅，过分夸张的罗马鼻，脑袋上缺乏"头发"。<br/>
                    </span>
                    <span class="feature">
                        [颈部]
                    </span>
                    <span class="info">
                        颈部有足够的长度，结实而圆拱，呈曲线状与肩部连接，肩胛长而向后倾斜。缺陷：颈部太短或太粗，羊脖子，鹅脖子，颈部缺乏肌肉或骨骼。<br/>
                    </span>
                    <span class="feature">
                        [身躯]
                    </span>
                    <span class="info">
                        从马肩隆到腰部，背线看起来几乎是完全水平的。腰部有力而结实，略微圆拱，消失在臀部。髋骨非常突出，肋骨扩张良好，而腰窝上提。肩高与体长（从胸到臀部的距离）大致相等，胸深，宽度适中。缺陷：弓背，凹陷的背部，鹅屁股，松弛的腰部，髋骨不突出，胸部太宽，而与肘部发生冲突。<br/>
                    </span>
                    <span class="feature">
                        [尾巴]
                    </span>
                    <span class="info">
                        尾根位置不过分高，呈环状或末端弯曲；但不能过分卷曲，或卷在背后，或甩向身体一侧；且决不能太粗。<br/>
                    </span>
                    <span class="feature">
                        [腿]
                    </span>
                    <span class="info">
                        前肢直而结实，从肘部到骹骨的长度很大，肘部贴合身体，前足爪的长度和宽度都很大，脚趾圆拱，足爪上覆盖着浓厚的长被毛，质地精细，骹骨长而直，脚垫非常大且支撑在地面上。肩关节角度恰当，使腿在身体下方合适的位置。太直的肩关节将导致骹骨被压垮，属于严重缺陷。阿富汗猎犬的四个足爪都与身躯保持同一方向，既不向内弯，也不向外翻。后足爪宽且有足够的长度，脚趾圆拱，足爪上覆盖着浓厚的长被毛。后躯有力而肌肉发达，飞节与臀部间有足够的长度；飞节位置低，飞节和膝关节的角度都非常恰当；从飞节到胯部略微弯曲呈弓形。缺陷：前后足爪向内弯或向外翻，脚垫缺乏足够的厚度，足爪太小，足爪有其他显著的缺点，骹骨松懈或被压垮，膝关节太直，飞节太长。<br/>
                    </span>
                    <span class="feature">
                        [眼睛]
                    </span>
                    <span class="info">
                        金色或暗色，上扬的东洋型眼角。<br/>
                    </span>
                    <span class="feature">
                        [耳朵]
                    </span>
                    <span class="info">
                        长，被丝毛覆盖，耳根位置低，紧贴两颊下垂。<br/>
                    </span>
                    <span class="feature">
                        [被毛]
                    </span>
                    <span class="info">
                        后躯，腰窝，肋部，前躯，和腿部都覆盖着浓密、丝状的毛发，质地细腻；耳朵，四个足爪都有羽状饰毛；从前面的肩部开始；向后面延伸为马鞍形区域（包括腰窝和肋骨以上部位）的毛发略短，且紧密，构成了成熟狗的平滑后背，这是阿富汗猎犬的传统特征。阿富汗猎犬是以自然形态出现，被毛不需要修剪或修整；在头顶上有长而呈丝状的"头发"，这也是阿富汗猎犬的显著特点。在前肢和后肢的腕部的毛发较短是允许的。缺陷：成熟的狗缺乏短毛的马鞍形区域。<br/>
                    </span>
                    <span class="feature">
                        [身高]
                    </span>
                    <span class="info">
                        雄性，27英寸，上下误差1英寸；雌性，25英寸，上下误差1英寸。<br/>
                    </span>
                    <span class="feature">
                        [体重]
                    </span>
                    <span class="info">
                        雄性大约60磅；雌性大约50磅。<br/>
                    </span>
                    <span class="feature">
                        [颜色]
                    </span>
                    <span class="info">
                        所有颜色都允许，但彩色或组合色更好。白色斑纹，尤其是出现在头部，则不理想。<br/>
                    </span>
                    <span class="feature">
                        [步态]
                    </span>
                    <span class="info">
                        自由奔跑时，阿富汗猎犬会高速飞奔，在有力而平顺的步伐中，显示出极大的弹性和弹力。如果不加约束，阿富汗猎犬奔跑的速度极快；向前奔跑时，后面的足爪直接落在前足爪的足迹上，（前后足迹）都是笔直向前的。跑动时，头部和尾巴都高高昂起，阿富汗猎犬的整个外观就是非常时尚，且非常漂亮。<br/>
                    </span>
                    <span class="feature">
                        [气质]
                    </span>
                    <span class="info">
                        孤傲而威严，给人一种从容淡定的感觉，但快乐。缺陷：凶狠或羞怯。
                    </span>
                </div>
            </div>
            <div class="baike-detail-row baike-detail-comment">
                <div class="baike-detail-title">小评</div>
                <div class="baike-detail-body">
                    <span class="comment">
                    高贵超然，但很活泼。能适应公寓生活，但必须给予大量的运动空间和机会，毛发长，需要经常精心打理，这也是让喜欢阿富汗的犬迷们最头痛的，它们好动的性格会让你两个小时的心血顷刻毁掉。就奔跑型猎犬而言，没有其他品种犬能像阿富汗猎犬这样外形漂亮、举止优雅、超凡脱俗而又不失威严庄重。在西方，阿富汗猎犬的培育完全摒弃了其功能作用，只求观赏效果。
                    </span>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
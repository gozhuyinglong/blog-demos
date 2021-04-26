# -*-coding:utf-8-*-
import requests
from bs4 import BeautifulSoup


# 根据地址获取页面内容，并返回BeautifulSoup
def get_html(url):
    # 若页面打开失败，则无限重试，没有后退可言
    while True:
        try:
            # 超时时间为1秒
            response = requests.get(url, timeout=1)
            response.encoding = "GBK"
            if response.status_code == 200:
                return BeautifulSoup(response.text, "lxml")
            else:
                continue
        except Exception:
            continue


# 获取地址前缀（用于相对地址）
def get_prefix(url):
    return url[0:url.rindex("/") + 1]


# 递归抓取下一页面
def spider_next(url, lev, parent_item_code):
    if lev == 2:
        spider_class = "city"
    elif lev == 3:
        spider_class = "county"
    elif lev == 4:
        spider_class = "town"
    else:
        spider_class = "village"

    item_list = get_html(url).select("tr." + spider_class + "tr")
    if len(item_list) > 0:
        for item in item_list:
            item_td = item.select("td")
            item_td_code = item_td[0].select_one("a")
            item_td_name = item_td[1].select_one("a")
            if item_td_code is None:
                item_href = None
                item_code = item_td[0].text
                item_name = item_td[1].text
                if lev == 5:
                    item_name = item_td[2].text
            else:
                item_href = item_td_code.get("href")
                item_code = item_td_code.text
                item_name = item_td_name.text
            # 输出：级别、区划代码、名称
            content2 = str(lev) + "\t" + item_code + "\t" + item_name + "\t" + parent_item_code
            print(content2)
            f.write(content2 + "\n")
            if item_href is not None:
                spider_next(get_prefix(url) + item_href, lev + 1, item_code)
    else:
        spider_next(url, lev + 1, parent_item_code)



# 入口
if __name__ == '__main__':

    # 抓取省份页面
    province_url = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2020/index.html"
    province_list = get_html(province_url).select('tr.provincetr a')

    # 数据写入到当前文件夹下 area-number-2020.txt 中
    f = open("area-number-2020.txt", "w", encoding="utf-8")
    try:
        for province in province_list:
            href = province.get("href")
            province_code = href[0: 2] + "0000000000"
            province_name = province.text
            # 输出：级别、区划代码、名称
            content = "1\t" + province_code + "\t" + province_name
            print(content)
            f.write(content + "\n")
            spider_next(get_prefix(province_url) + href, 2, province_code)
    finally:
        f.close()

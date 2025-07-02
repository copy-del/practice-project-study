#!/usr/bin/env python
# coding: utf-8

# anaconda prompt 창을 켜서 -> 해당파일이 있는 주소로 경로 이동(cd)
# 실행명령어 uvicorn main:app --reload

# # 필요한 라이브러리 설치
# - fastapi : python쪽에 요청을 처리할 수 있는 서버를 로드하는 라이브러리(flask 라이브러리와 유사)
# - uvicorn : 서버 재요청을 할때마다, 서버를 껐다 키는게 불편하니까 사용하기 쉬우려고 추가하는 라이브러리

# In[2]:


# get_ipython().system('pip install fastapi')


# In[4]:


# get_ipython().system('pip install uvicorn[standard]')


# In[9]:


# get_ipython().system('pip install nbconvert')


# In[ ]:





# # FastAPI 사용하기

# In[7]:


# 1. 라이브러리 가져오기
from fastapi import FastAPI
# 동일출저 정책을 풀어줄 수 있는 설정을 잡아보자
from fastapi.middleware.cors import CORSMiddleware
# In[11]:
from selenium import webdriver as wb
from selenium.webdriver.common.by import By
#  크롤링 함수 --> 저런 코드들을 다 가지고있는 모듈을 따로 분리하는 거 공부해기기
def crawling(search) :
    driver = wb.Chrome()
    driver.get(f'https://search.naver.com/search.naver?ssc=tab.image.all&where=image&sm=tab_jum&query={search}')
    driver.implicitly_wait(5)
    # 3. img 태그 선택해서 src 값 가져오기
    img = driver.find_element(By.CSS_SELECTOR,"#main_pack > section > div.api_subject_bx._fe_image_tab_grid_root.ani_fadein > div > div > div.image_tile._fe_image_tab_grid > div:nth-child(1) > div > div > div > img")
    img_src = img.get_attribute("src")
    # 4. chrome창 닫기
    driver.quit()
    return img_src

# 2. FastAPI를 기반으로 한 app 생성
app = FastAPI()

# app == controller와 유사한 역할
# app에다가 middleware를 끼우는 작업 진행 --> 정책을 좀 풀어주려고!
app.add_middleware(
    # 동일출처정책과 관련한 미들웨어
    CORSMiddleware,
    # 접근을 허용할 출처(==접근을 허용할 url)
    allow_origins = ['http://localhost:8090'],
    allow_methods = ["*"],
    allow_headers = ["*"],
    allow_credentials = True
)

# 3. 요청을 할 수 있는 url - 함수를 생성
@app.get("/temp")
# 수집해야하는 데이터가 있다면, parameter로 받아오면 된다.
# --> parameter의 이름은 보내주는 data의 key값과 일치해야한다.
def temp(sendData):
    print("data : "+ sendData)
    img_src = crawling(sendData)
    return {"img_src" : img_src}


# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





# In[ ]:





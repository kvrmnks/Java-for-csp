#include <iostream>
#include <cstring>
#include <queue>
#include <cstdio>
using namespace std;
const int Maxn=200200;
queue <int> q;
int n,m,s,t,Max;
int head[Maxn],deep[Maxn],cur[Maxn];
struct nxt_list
{
	int fr,to,nxt,val;
}e[Maxn];
int read()
{
	int rt=0,in=1;
	char ch=getchar();
	while(ch<'0'||ch>'9'){if(ch=='-')	in=-1;ch=getchar();}
	while(ch>='0'&&ch<='9'){rt=rt*10+ch-'0';ch=getchar();}
	return rt*in;
}
void add_edge(int u,int v,int val)
{
	e[++head[0]].to=v;
	e[head[0]].fr=u;
	e[head[0]].nxt=head[u];
	e[head[0]].val=val;
	head[u]=head[0];
}
bool bfs()
{
	memset(deep,0,sizeof(deep));
	deep[s]=1,q.push(s);
	while(!q.empty())
	{
		int x=q.front();
		q.pop();
		for(int i=head[x]; i; i=e[i].nxt)
			if(!deep[e[i].to]&&e[i].val>0)
			{
				deep[e[i].to]=deep[x]+1;
				q.push(e[i].to);
			}
	}
	return deep[t] ? true : false;
} 
int dfs(int x,int val)
{
	if(x==t)	return val;
	int w=0,u=0;
	for(int i=head[x]; i; i=e[i].nxt)
		if(deep[e[i].to]==deep[x]+1)
		{
			w=dfs(e[i].to,min(e[i].val,val-u));
			e[i].val-=w,e[i^1].val+=w;
			u+=w;
			if(e[i].val)	cur[x]=i;
			if(u==val)	return u;
		}
	return u;
}
void dinic()
{
	while(bfs())
	{
		for(int i=1; i<=n; i++)	cur[i]=head[i];
		Max+=dfs(s,0x7ffffff);
	}
	printf("%d\n",Max);
}

int main()
{
	m=read(),n=read(),s=n+1,t=s+1,head[0]=1;
	for(int i=1; i<=m; i++)
		add_edge(s,i,1),add_edge(i,s,0);
	for(int i=m+1; i<=n; i++)
		add_edge(i,t,1),add_edge(t,i,0);
	while(1)
	{
		int u=read(),v=read();
		if(u==-1 && v==-1)	break;
		add_edge(u,v,1),add_edge(v,u,0);
	}
	dinic();
	for(int i=3;i<=head[0];i+=2)
		if(e[i].fr!=s&&e[i].fr!=t&&e[i].to!=t&&e[i].to!=s&&e[i].val)
			printf("%d %d\n",e[i].to,e[i].fr);
	return 0;
}


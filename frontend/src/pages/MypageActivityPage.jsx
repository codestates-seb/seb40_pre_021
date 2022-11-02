import styled from 'styled-components';
import Answers from '../components/Mypage/Activity/Answers/Answers';
import Questions from '../components/Mypage/Activity/Questions/Questions';
import Reputation from '../components/Mypage/Activity/Reputation/Reputation';
import Sidebar from '../components/Mypage/Activity/Sidebar';
import Summary from '../components/Mypage/Activity/Summary';
import Tags from '../components/Mypage/Activity/Tags/Tags';
import useDynamicTitle from '../hooks/useDynamicTitle';
import useMypageData from '../hooks/useMypageData';

const MypageActivityPage = () => {
	const [answer, setAnswer] = useMypageData('answer');
	const [question, setQuestion] = useMypageData('question');
	const [tag, setTag] = useMypageData('tag');
	useDynamicTitle('User', true);

	return (
		<SideBarBox>
			<Sidebar />
			<GridBox>
				<Summary />
				<Answers answer={answer} />
				<Questions question={question} />
				<Tags tag={tag} />
				<Reputation />
			</GridBox>
		</SideBarBox>
	);
};

export default MypageActivityPage;

const SideBarBox = styled.div`
	display: flex;
	margin: 8px 0;
	flex-wrap: nowrap;
`;

const GridBox = styled.div`
	display: grid;
	grid-template-columns: repeat(2, minmax(0, 1fr));
	gap: 24px;
	margin: 8px 0 8px 8px;
`;

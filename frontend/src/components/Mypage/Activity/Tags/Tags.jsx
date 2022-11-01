import styled from 'styled-components';
import useMypageData from '../../../../hooks/useMypageData';
import ListBox from '../ListBox';
import Title from '../Title';
import TagsList from './TagsList';

const Tags = () => {
	const [data] = useMypageData('tag');
	return (
		<Container>
			<TitleBox>
				<Title title="Tags" number={data?.length} flex={true} />
			</TitleBox>
			<ListBox
				text="You have not participated in any tags"
				lists={data}
				component={<TagsList lists={data} />}
			/>
		</Container>
	);
};

export default Tags;

const Container = styled.div`
	&&& {
		height: 100%;
		display: flex;
		flex-direction: column;
	}
`;

const TitleBox = styled.div`
	display: flex;
	margin-bottom: 8px;
	align-items: flex-end;
	justify-content: space-between;
`;

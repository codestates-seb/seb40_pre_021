import styled from 'styled-components';
import Button from '../common/Button';
import ListTab from './ListTab';

const ListHeaderStyle = styled.div`
	padding: 24px;

	section {
		padding: 12px;
		display: flex;
		align-items: center;
	}
`;

const Section = styled.section`
	justify-content: space-between;

	.title {
		font-size: 32px;
		font-weight: 500;
	}
`;
const BottomSection = styled.section`
	justify-content: ${(props) => (props.filter ? 'space-between' : 'flex-end')};
`;

const ListHeader = ({ title, Detail, filter, tabList }) => {
	return (
		<ListHeaderStyle>
			<Section>
				<div className="title">{title}</div>
				<Button text="Ask Question" />
			</Section>
			<Section>{Detail}</Section>
			<BottomSection filter={filter}>
				<ListTab tabList={tabList} filter={filter} />
			</BottomSection>
		</ListHeaderStyle>
	);
};

export default ListHeader;
